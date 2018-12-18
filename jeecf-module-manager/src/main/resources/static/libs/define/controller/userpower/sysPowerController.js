define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() { 
			$httpRequest.post($ctx.getWebPath()+"userpower/sysPower/save", $scope.sysPower).then(
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
			$httpRequest.post($ctx.getWebPath()+"userpower/sysPower/list",
			$scope.request.data).then(function(res) { 
				if (res.success) {
					$scope.sysPowerList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "userpower/sysPower/delete/"+$scope.sysPowerList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "userpower/sysPower/save",
					$scope.updateSysPower).then(function(res) {
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
			$scope.sysPower.name = undefined;
			$scope.sysPower.permission = undefined;
			$scope.sysPower.parentId = $scope.sysPowerList[index].id;
			$scope.sysPower.sort = parseInt($scope.sysPowerList[index].sort)+10;
		    $('#sysPowerFormTab #insertTreeId').find('input[name="selectedName"]').val($scope.sysPowerList[index].name);
			$scope.sysPower.remark = undefined;
			$('#top-tab a[href="#sysPowerFormTab"]').tab('show');
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal #updateTreeId').find('input[name="selectedName"]').val(null);
			$('#updateModal').modal('show');
			angular.copy($scope.sysPowerList[index], $scope.updateSysPower);
			if($scope.updateSysPower.parent != null){
			   $('#updateModal #updateTreeId').find('input[name="selectedName"]').val($scope.updateSysPower.parent.name);
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
			$scope.updateSysPower = {};
			$scope.sysPower = {};
			$page.init($scope, $page.getPageSize());
			$scope.initSection();
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
				   $jBoxcm.treeTable($scope, this, $scope.sysPower);
			   } else if(id == "updateTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.updateSysPower);
			   }
			});  
		}

		$scope.renderFinish = function(e) {
			$("#sysPowerListTreeTable").treetable({
				expandable : true
			},true);
		}
	};
});