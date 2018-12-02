define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath()+"template/genField/save", $scope.genField).then(
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
			
			$httpRequest.post($ctx.getWebPath()+"template/genField/list",
			$scope.request).then(function(res) { // 调用承诺API获取数据 .resolve
				if (res.success) {
					$scope.genFieldList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "template/genField/delete/"+$scope.genFieldList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "template/genField/save",
					$scope.updateGenField).then(function(res) {
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
			$httpRequest.post($ctx.getWebPath()+"template/genField/column/"+$scope.genFieldList[index].id).then(
					function(res) { 
				          if(res.success){
				        	  $scope.genFieldList[index].genFieldColumn = res.data;
				      		  angular.copy($scope.genFieldList[index], $scope.updateGenField);
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
			$scope.updateGenField = {genFieldColumn:[]};
			$scope.genField = {genFieldColumn:[{},{}]};
			$page.init($scope, $page.getPageSize());
		}
		
		$scope.addGenFieldColumn = function(){
			$scope.genField.genFieldColumn.push({});
		}
		
		$scope.updateAddGenFieldColumn = function(){
			$scope.updateGenField.genFieldColumn.push({});
		}
		
		$scope.deleteGenFieldColumn = function(index){
			var genFieldColumn = $scope.genField.genFieldColumn;
			var tempFieldColumn = [];
			for(var i = 0 ; i  < genFieldColumn.length; i++) {
			    if(i != index) {
			    	tempFieldColumn.push(genFieldColumn[i]);
			    }
			}
			$scope.genField.genFieldColumn = tempFieldColumn;
		}
		
		$scope.updateDeleteGenFieldColumn = function(index){
			var genFieldColumn = $scope.updateGenField.genFieldColumn;
			var tempFieldColumn = [];
			for(var i = 0 ; i  < genFieldColumn.length; i++) {
			    if(i != index) {
			    	tempFieldColumn.push(genFieldColumn[i]);
			    }
			}
			$scope.updateGenField.genFieldColumn = tempFieldColumn;
		}
		
		$scope.detailModal = function(index){
			
			$httpRequest.post($ctx.getWebPath()+"template/genField/column/"+$scope.genFieldList[index].id).then(
					function(res) { 
				          if(res.success){
				        	  $scope.detailField = {};
				        	  $scope.genFieldList[index].genFieldColumn = res.data;
				        	  for(var i in $scope.genFieldList[index].genFieldColumn){
				        		  if($scope.genFieldList[index].genFieldColumn[i].isNull == 1){
				        			  $scope.genFieldList[index].genFieldColumn[i].isNullName = "是";
				        		  } else {
				        			  $scope.genFieldList[index].genFieldColumn[i].isNullName = "否";
				        		  }
				        	  }
				      		  angular.copy($scope.genFieldList[index], $scope.detailField);
				      		  
				  			  $('#detailModal').modal('show');
		                  }                 
					});
		}
	};
});