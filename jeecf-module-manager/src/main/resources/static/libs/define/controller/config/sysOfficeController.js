define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() { 
			$httpRequest.post($ctx.getWebPath()+"config/sysOffice/save", $scope.sysOffice).then(
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
			$httpRequest.post($ctx.getWebPath()+"config/sysOffice/list",
			$scope.request).then(function(res) { 
				if (res.success) {
					$scope.sysOfficeList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "config/sysOffice/delete/"+$scope.sysOfficeList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "config/sysOffice/save",
					$scope.updateSysOffice).then(function(res) {
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
			$scope.sysOffice.name = undefined;
			$scope.sysOffice.enname = undefined;
			$scope.sysOffice.parentId = $scope.sysOfficeList[index].id;
			$scope.sysOffice.sort = parseInt($scope.sysOfficeList[index].sort)+10;
		    $('#sysOfficeFormTab #insertTreeId').find('input[name="selectedName"]').val($scope.sysOfficeList[index].name);
			$scope.sysOffice.remark = undefined;
			$('#top-tab a[href="#sysOfficeFormTab"]').tab('show');
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal').modal('show');
			angular.copy($scope.sysOfficeList[index], $scope.updateSysOffice);
			if($scope.updateSysOffice.parent != null){
			   $('#updateModal #updateTreeId').find('input[name="selectedName"]').val($scope.updateSysOffice.parent.name);
			}
		}
		
		$scope.initPageBack = function(request) {
			$scope.request.page = null;
			return $scope.searchForm();
		};
		
		this.init = function(){
		    $scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {page:{current:"",size:""},data:{}};
			$scope.updateSysOffice = {};
			$scope.sysOffice = {};
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
				   $jBoxcm.treeTable($scope, this, $scope.sysOffice);
			   } else if(id == "updateTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.updateSysOffice);
			   }
			});  
		}

		$scope.renderFinish = function(e) {
			$("#sysOfficeListTreeTable").treetable({
				expandable : true
			},true);
		}
	};
});