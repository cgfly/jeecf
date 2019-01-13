define([ 'app','$httpRequest','$page','$ctx','$jBoxcm'], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() { 
			$httpRequest.post($ctx.getWebPath()+"gen/sysTreeDict/save", $scope.sysTreeDict).then(
					function(res) { 
				          if(res.success){
		                	  $jBoxcm.success("保存数据成功");
		                	  $state.reload($scope.currentRouteName);
		                  } else {
		                	  $jBoxcm.error("保存数据失败,"+res.errorMessage);
		                  }		                
					});
		};
		
		$scope.searchForm = function() {
			$httpRequest.post($ctx.getWebPath()+"gen/sysTreeDict/list",
			$scope.request.data).then(function(res) { 
				if (res.success) {
					$scope.sysTreeDictList = res.data;
					$page.setPage($scope,0);
					setTimeout(function(){
						$jBoxcm.delConfrim($scope);
					},800);
				} else {
					$jBoxcm.error("查询数据失败,"+res.errorMessage);
				}
			});
		};
		
		$scope.deleteForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "gen/sysTreeDict/delete/"+$scope.sysTreeDictList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "gen/sysTreeDict/save",
					$scope.updateSysTreeDict).then(function(res) {
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
			$scope.sysTreeDict.name = undefined;
			$scope.sysTreeDict.parentId = $scope.sysTreeDictList[index].id;
			$scope.sysTreeDict.sort = parseInt($scope.sysTreeDictList[index].sort)+10;
			$scope.sysTreeDict.groupName =  $scope.sysTreeDictList[index].groupName;
		    $('#sysTreeDictFormTab #insertTreeId').find('input[name="selectedName"]').val($scope.sysTreeDictList[index].name);
			$('#top-tab a[href="#sysTreeDictFormTab"]').tab('show');
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal #updateTreeId').find('input[name="selectedName"]').val(null);
			$('#updateModal').modal('show');
			angular.copy($scope.sysTreeDictList[index], $scope.updateSysTreeDict);
			if($scope.updateSysTreeDict.parent != null){
			   $('#updateModal #updateTreeId').find('input[name="selectedName"]').val($scope.updateSysTreeDict.parent.name);
			}
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
			$scope.updateSysTreeDict = {};
			$scope.sysTreeDict = {};
			$scope.sysPower = {};
			$page.init($scope, $page.getPageSize());
			$scope.initSection();
			$ctx.getEnum($rootScope,"treeEventEnum",function(result){
				$scope.$apply(function () {
					$scope.treeEventEnums = result;
				});
			});
		}
		
		
		$scope.initSection = function(){
			if($("#insertTreeIdTable").html() != undefined){
				$("#insertTreeIdTable").remove();
			}
			if($("#updateTreeIdTable").html() != undefined){
				$("#updateTreeIdTable").remove();
		    }
			$(".treeTable ").each(function() {
			   var id = $(this).attr("id");
			   if(id == "insertTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.sysTreeDict);
			   } else if(id == "updateTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.updateSysTreeDict);
			   }
			});  
		}

		$scope.renderFinish = function(e) {
			$("#sysTreeDictListTreeTable").treetable({
				expandable : true
			},true);
		}
	};
});