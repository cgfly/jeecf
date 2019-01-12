define([ 'app', '$httpRequest', '$page', '$ctx', '$jBoxcm' ], function(app,
		$httpRequest, $state, $page, $ctx, $jBoxcm) {
	return function($scope, $rootScope, $httpRequest, $state, $page, $ctx,
			$jBoxcm) {

		$scope.submitForm = function() {
			$('#addFileModal').modal('show');
		};
		
		$scope.addConfirmForm = function(){
			var fd = new FormData();
			var file = document.querySelector('input[id=addFile]').files[0];
	        fd.append('file', file); 
	        $scope.upload(fd,$ctx.getWebPath() + "extend/sysOsgiPluginAll/upload",$scope.sysOsgiPluginAll,'#addFileModal');
		}
		

		$scope.searchForm = function(message) {
			var pageId = 1;
			if (message != undefined) {
		        pageId = $page.getPageId(message);
				if(pageId == -1){
					return;
				}
			} 
			$scope.query.page = pageId;
			$scope.request.page.current = pageId;
			$httpRequest.post($ctx.getWebPath() + "extend/sysOsgiPluginAll/list",
					$scope.request).then(function(res) { 
				if (res.success) {
					data = res.data;
					if(data != undefined && data.length> 0){
						$scope.delFlag =$scope.delFlags[res.data[0].delFlag];
						if(res.data[0].delFlag == 1){
							$scope.delFlagVisible = false;
						} else {
							$scope.delFlagVisible = true;
						}
					}
					$scope.sysOsgiPluginAllList = data;
					$page.setPage($scope, res.page.total);
					setTimeout(function(){
						$jBoxcm.delConfrim($scope);
					},800);
				} else {
					$jBoxcm.error("查询数据失败," + res.errorMessage);
				}
			});
		};

		$scope.deleteForm = function(index) {
			$httpRequest.post(
					$ctx.getWebPath() + "extend/sysOsgiPluginAll/delete/"+$scope.sysOsgiPluginAllList[index].id).then(function(res) {
				if (res.success) {
					$jBoxcm.success("删除数据成功");
					$state.reload($scope.currentRouteName);
				} else {
					$jBoxcm.error("删除数据失败," + res.errorMessage);
				}
			});
		};

		$scope.updateForm = function() {
			$httpRequest.post($ctx.getWebPath() + "extend/sysOsgiPluginAll/save",
					$scope.updateSysOsgiPluginAll).then(function(res) {
				if (res.success) {
					$('#updateModal').modal('hide');
					setTimeout(function() {
						$state.reload($scope.currentRouteName);
						$jBoxcm.success("修改数据成功");
					}, 500);
				} else {
					$jBoxcm.error("修改数据失败," + res.errorMessage);
				}
			});
		}

		$scope.updateModal = function(index) {
			$('#updateModal').modal('show');
			angular.copy($scope.sysOsgiPluginAllList[index],$scope.updateSysOsgiPluginAll);
		}

		$scope.initPageBack = function(request) {
			if ($scope.query == undefined || $scope.query.page == undefined) {
				$scope.request.page.current = request.page.current;
			} else {
				$scope.request.page.current = $scope.query.page;
			}
			$scope.request.page.size = request.page.size;
			return $scope.searchForm();
		};
		
		this.init = function() {
			$scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.delFlags = [{"name":"激活","value":0,"action":"失效"},{"name":"失效","value":1,"action":"激活"}];
			$scope.request = {page : {current : "",size : ""},data : {}};
			$scope.updateSysOsgiPluginAll = {};
			$scope.sysOsgiPluginAll = {};
			$page.init($scope, $page.getPageSize());
			$ctx.getEnum($rootScope,"osgiBoundleTypeEnum",function(result){
				$scope.$apply(function () {
					$scope.osgiBoundleTypeEnums = result;
				});
			});
		}
		
		$scope.updateFileModal = function(index){
			angular.copy($scope.sysOsgiPluginAllList[index],$scope.updateSysOsgiPluginAll);
			$('#updateFileModal').modal('show');
		}
		
		
		$scope.upload = function(fd,url,sysOsgiPluginAll,fileId){
			 $httpRequest.upload(url,fd).then(function(res) {
		        		$(fileId).modal('hide');
						if (res.success) { 
							sysOsgiPluginAll.fileName = res.data;
							$httpRequest.post($ctx.getWebPath() + "extend/sysOsgiPluginAll/save",
									sysOsgiPluginAll).then(function(res) {
							    console.log(res);
								if (res.success) {
									 setTimeout(function() {
										 $jBoxcm.success("保存数据成功");
										 $state.reload($scope.currentRouteName);
				  					  }, 500);
								} else {
									  console.log(res.errorMessage);
									$jBoxcm.error("保存数据失败," + res.errorMessage);
								}
							});
						} else {
							$jBoxcm.error("保存数据失败," + res.errorMessage);
						}
					});
		}
		
		$scope.downloadModal = function (index){
			var form = $httpRequest.form($ctx.getWebPath() + "extend/sysOsgiPluginAll/download/plugin/"+$scope.sysOsgiPluginAllList[index].id,"POST");
       	 	form.submit();
		}
		
		$scope.gainModal = function (index){
			$httpRequest.post(
					$ctx.getWebPath() + "extend/sysOsgiPluginAll/gain/"+$scope.sysOsgiPluginAllList[index].id).then(function(res) {
				if (res.success) {
					$jBoxcm.success("获取数据成功");
				} else {
					$jBoxcm.error("获取数据失败," + res.errorMessage);
				}
			});
		}
		
		$scope.actionForm = function(index) {
			if($scope.delFlag.value == 0){
				$scope.invalidForm(index);
			} else if($scope.delFlag.value == 1){
				$scope.activeForm(index);
			}
		}
		
		$scope.invalidForm = function(index){
			$httpRequest.post($ctx.getWebPath() + "extend/sysOsgiPluginAll/invalid/"+$scope.sysOsgiPluginAllList[index].id).then(function(res) {
				if(res.success){
				  	$jBoxcm.success("操作成功");
				  	$state.reload($scope.currentRouteName);
				 } else {
				  	$jBoxcm.error("操作失败,"+res.errorMessage);
				 }
			});
		}
		
		$scope.activeForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "extend/sysOsgiPluginAll/active/"+$scope.sysOsgiPluginAllList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("激活数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("激活数据失败,"+res.errorMessage);
				  		}
			});
		};
		
	};
});