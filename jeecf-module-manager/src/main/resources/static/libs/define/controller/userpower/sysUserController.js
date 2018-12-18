define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm) {
		
		$scope.submitForm = function() {
			console.log($scope.sysUser);
			$httpRequest.post($ctx.getWebPath()+"userpower/sysUser/save", $scope.sysUser).then(
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
			
			$httpRequest.post($ctx.getWebPath()+"userpower/sysUser/list",
			$scope.request).then(function(res) { 
				if (res.success) {
					$scope.sysUserList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "userpower/sysUser/delete/"+$scope.sysUserList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "userpower/sysUser/save",
					$scope.updateSysUser).then(function(res) {
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
			$('#updateModal #updateOfficeTreeId').find('input[name="selectedName"]').val(null);
			angular.copy($scope.sysUserList[index], $scope.updateSysUser);
			if($scope.updateSysUser.sysOffice != null ){
				   $('#updateModal #updateOfficeTreeId').find('input[name="selectedName"]').val($scope.updateSysUser.sysOffice.name);
		    }
			$httpRequest.post($ctx.getWebPath()+"userpower/sysUser/roles/"+$scope.updateSysUser.id).then(function(res) { 
				if (res.success) {
					var data = res.data;
					$scope.sysRoleData = data;
		    		var roles = [];
		    		for(var i in data ){
		    			if(data[i].checked){
		    			   roles.push(data[i].id);
		    			}
		    		}
		    		setTimeout(function(){
		    			$("#updateRole").val(roles).trigger("change");
		    			$('#updateModal').modal('show');
					},500);
				} else {
					$jBoxcm.error("查询数据失败,"+res.errorMessage);
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
			$scope.updateSysUser = {};
			$scope.sysUser = {}
			$page.init($scope, $page.getPageSize());
			$scope.initSection();
			$('#top-tab a[href="#sysUserFormTab"]').on('shown.bs.tab', function(e){
				if($scope.sysRoleData == undefined){
					$scope.queryRoles();
				}
		    });
		}
		
		$scope.queryRoles = function() {
			$httpRequest.post($ctx.getWebPath()+"userpower/sysUser/roles/-1").then(function(res) {
				if (res.success) {
					$scope.sysRoleData = res.data;
				}	
			});
		}
		
		$scope.initSection = function(){
			if($("#insertOfficeTreeIdTable").html() != undefined){
				$("#insertOfficeTreeIdTable").remove();
			}
			if($("#updateOfficeTreeIdTable").html() != undefined){
				$("#updateOfficeTreeIdTable").remove();
		    }
			$(".treeTable ").each(function() {
			   var id = $(this).attr("id");
			   if(id == "insertOfficeTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.sysUser);
			   }  else if(id == "updateOfficeTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.updateSysUser);
			   }
			});  
		}
	};
});