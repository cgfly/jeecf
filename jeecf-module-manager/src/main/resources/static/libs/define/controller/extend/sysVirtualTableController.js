define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath()+"extend/sysVirtualTable/save", $scope.sysVirtualTable).then(
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
			$httpRequest.post($ctx.getWebPath()+"extend/sysVirtualTable/list",
			$scope.request).then(function(res) { 
				if (res.success) {
					$scope.sysVirtualTableList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "extend/sysVirtualTable/delete/"+$scope.sysVirtualTableList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "extend/sysVirtualTable/save",
					$scope.updateSysVirtualTable).then(function(res) {
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
		
		$scope.updateModal = function(index) {
			console.log($scope.sysVirtualTableList[index].id);
			$httpRequest.post($ctx.getWebPath()+"extend/sysVirtualTable/column/"+$scope.sysVirtualTableList[index].id).then(
					function(res) { 
				          if(res.success){
				        	  console.log(res.data);
				        	  $scope.sysVirtualTableList[index].sysVirtualTableColumns = res.data;
				      		  angular.copy($scope.sysVirtualTableList[index], $scope.updateSysVirtualTable);
				  			  $('#updateModal').modal('show');
		                  }                 
					});
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
			$scope.updateSysVirtualTable = {sysVirtualTableColumns:[]};
			$scope.sysVirtualTable = {sysVirtualTableColumns:[{},{}]};
			$page.init($scope, $page.getPageSize());
			$ctx.getEnum($rootScope,"tableTypeEnum",function(result){
				$scope.$apply(function () {
					$scope.tableTypeEnums = result;
				});
			});
		}
		
		$scope.addSysVirtualTableColumn = function(){
			$scope.sysVirtualTable.sysVirtualTableColumns.push({});
		}
		
		$scope.addUpdateSysVirtualTableColumn = function(){
			$scope.updateSysVirtualTable.sysVirtualTableColumns.push({});
		}
		
		$scope.deleteSysVirtualTableColumn = function(index){
			var sysVirtualTableColumns = $scope.sysVirtualTable.sysVirtualTableColumns;
			var tempSysVirtualTableColumns = [];
			for(var i = 0 ; i  < sysVirtualTableColumns.length; i++) {
			    if(i != index) {
			    	tempSysVirtualTableColumns.push(sysVirtualTableColumns[i]);
			    }
			}
			$scope.sysVirtualTable.sysVirtualTableColumns = tempSysVirtualTableColumns;
		}
		
		$scope.deleteUpdateSysVirtualTableColumn = function(index){
			var sysVirtualTableColumns = $scope.updateSysVirtualTable.sysVirtualTableColumns;
			var tempSysVirtualTableColumns = [];
			for(var i = 0 ; i  < sysVirtualTableColumns.length; i++) {
			    if(i != index) {
			    	tempSysVirtualTableColumns.push(sysVirtualTableColumns[i]);
			    }
			}
			$scope.updateSysVirtualTable.sysVirtualTableColumns = tempSysVirtualTableColumns;
		}
		
	};
});