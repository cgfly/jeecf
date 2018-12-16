define([ 'app', '$httpRequest','$page','$ctx','$genHelper','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$genHelper,$jBoxcm) {
		
		$scope.submitForm = function() { 
			$httpRequest.post($ctx.getWebPath()+"gen/sysTableDict/save", $scope.sysTableDict).then(
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
			var url = $ctx.getWebPath()+"gen/sysTableDict/list";
			var pageId = 1;
			if (message != undefined) {
		        pageId = $page.getPageId(message);
				if(pageId == -1){
					return;
				}
			} 
			$scope.query.page = pageId;
			$scope.request.page.current = pageId;
			var queryData = $scope.request.data;
			if(queryData != undefined && queryData.tableName != undefined && queryData.tableName == "-- 全部 --"){
				$scope.request.data.tableName  = undefined;
			}
			$httpRequest.post(url,
			$scope.request).then(function(res) { 
				if (res.success) {
					$scope.sysTableDictList = res.data;
					$page.setPage($scope,res.page.total);
					setTimeout(function(){
						$jBoxcm.delConfrim($scope);
					},800);
					
				} else {
					$jBoxcm.error("查询数据失败,"+res.errorMessage);
				}
			});
		};
		
		$scope.deleteForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "gen/sysTableDict/delete/"+$scope.sysTableDictList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "gen/sysTableDict/save",
					$scope.updateSysTableDict).then(function(res) {
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
		
		$scope.addModal = function(index){
			$scope.sysTableDict.name = $scope.sysTableDictList[index].name;
			$scope.sysTableDict.description = $scope.sysTableDictList[index].description;
			$('#top-tab a[href="#sysTableDictFormTab"]').tab('show');
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal').modal('show');
			angular.copy($scope.sysTableDictList[index], $scope.updateSysTableDict);
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
		    $scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {page:{current:"",size:""},data:{}};
			$scope.updateSysTableDict = {};
			$scope.sysTableDict = {};
			$scope.queryBaseTableList();
			$page.init($scope, $page.getPageSize());
		}
	
		
		$scope.queryBaseTableList = function() {
			$httpRequest.post("/gen/sysTableDict/tables").then(
					function(res) { // 调用承诺API获取数据 .resolve
						if(res.success){
							$scope.tableList = res.data;
							$scope.searchTableList = [{"tableName":"-- 全部 --"}]
							if(res.data.length > 0){
								for(var i = 0; i < res.data.length;i++){
									$scope.searchTableList.push( {"tableName":res.data[i].name});
								}
								$scope.sysTableDict.tableName = $scope.tableList[0].name;
								$scope.sysTableDict.comment = $scope.tableList[0].comment;
							}
						}
					});
		};
		
		$scope.insertTableNameChange = function(nv, ov){
			if(nv != undefined){
				var field = $genHelper.toField(nv);
				if(field != 'undefined'){
					$scope.sysTableDict.field = field;
					var tables = $scope.tableList;
					if(tables != undefined){
						for(var i = 0; i < tables.length;i++){
							if(tables[i].name == nv){
								$scope.sysTableDict.comment = tables[i].comment;
							}
						}
					}
				}
			}
		}
		
		$scope.updateTableNameChange = function(nv, ov){
			if(nv != undefined){
				var field = $genHelper.toField(nv);
				if(field != 'undefined'){
					$scope.updateSysTableDict.field = field;
				}
			}
		}
		
	};
});