define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath()+"config/sysDbsource/save", $scope.sysDbsource).then(
					function(res) { 
				          if(res.success){
		                	  $jBoxcm.success("保存数据成功");
		                	  $state.reload($scope.currentRouteName);
		                  } else {
		                	  $jBoxcm.error("保存数据失败,"+res.errorMessage);
		                  }		                
					});
		};
		
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
			
			$httpRequest.post($ctx.getWebPath()+"config/sysDbsource/list",
			$scope.request).then(function(res) { 
				if (res.success) {
					if(res.data != undefined && res.data.length> 0){
						$scope.delFlag =$scope.delFlags[res.data[0].delFlag];
						if(res.data[0].delFlag == 1){
							$scope.delFlagVisible = false;
						} else {
							$scope.delFlagVisible = true;
						}
					}
					$scope.sysDbsourceList = res.data;
					$page.setPage($scope,res.page.total);
				} else {
					$jBoxcm.error("查询数据失败,"+res.errorMessage);
				}
			});
		};
		$scope.actionForm = function(index) {
			if($scope.delFlag.value == 0){
				$scope.invalidForm(index);
			} else if($scope.delFlag.value == 1){
				$scope.activeForm(index);
			}
		}
		$scope.invalidForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysDbsource/invalid/"+$scope.sysDbsourceList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("数据已失效");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("操作失败,"+res.errorMessage);
				  		}
			});
		};
		
		$scope.activeForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysDbsource/active/"+$scope.sysDbsourceList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("激活数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("激活数据失败,"+res.errorMessage);
				  		}
			});
		};
		
		$scope.syncGenForm  = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysDbsource/syncGen/"+$scope.sysDbsourceList[index].id).then(function(res) {
		  		if(res.success){
		  			$jBoxcm.success("同步数据成功");
		  		} else {
		  			$jBoxcm.error("同步数据失败,"+res.errorMessage);
		  		}
			});
		}

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "config/sysDbsource/save",
					$scope.updateSysDbsource).then(function(res) {
		                  if(res.success){
		                	  $('#updateModal').modal('hide');
		  					  setTimeout(function() {
		  						 $state.reload($scope.currentRouteName);
		  						 $jBoxcm.success("修改数据成功");
		  					  }, 500);
		                  } else {
		                	  $jBoxcm.error("修改数据失败,"+res.errorMessage);
		                  }
			});
		}
		$scope.effectForm = function(index){
			$httpRequest.post($ctx.getWebPath() + "config/sysDbsource/effect/"+$scope.sysDbsourceList[index].id).then(function(res) {
				if(res.success){
				  	$jBoxcm.success("操作成功");
				  	$state.reload($scope.currentRouteName);
				 } else {
				  	$jBoxcm.error("操作失败,"+res.errorMessage);
				 }
			});
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal').modal('show');
			angular.copy($scope.sysDbsourceList[index], $scope.updateSysDbsource);
		}
		
		$scope.initPageBack = function(request) {
			if($scope.query == undefined || $scope.query.page == undefined){
				$scope.request.page.current = request.page.current;
			} else {
				$scope.request.page.current = $scope.query.page;
			}
			$scope.request.page.size = request.page.size;
			return $scope.searchForm();
		};
		
		this.init = function(){
			$scope.delFlags = [{"name":"激活","value":0,"action":"失效"},{"name":"失效","value":1,"action":"激活"}];
		    $scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {page:{current:"",size:""},data:{}};
			$scope.updateSysDbsource = {};
			$scope.sysDbsource = {};
			$page.init($scope, $page.getPageSize());
			$scope.queryPermissions();
		}
		
		$scope.queryPermissions = function() {
			$httpRequest.post($ctx.getWebPath()+"config/sysDbsource/permissions").then(function(res) {
				if (res.success) {
					$scope.permissions = res.data;
				}	
			});
		}
		
		
	};
});