define([ 'app', '$httpRequest','$page','$ctx','$jBoxcm','$zTreecm' ], function(app, $httpRequest,$state,$page,$ctx,$jBoxcm,$zTreecm) {
	return function($scope, $rootScope,$httpRequest,$state,$page,$ctx,$jBoxcm,$zTreecm) {
		
		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath()+"userpower/sysRole/save", $scope.sysRole).then(
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
			
			$httpRequest.post($ctx.getWebPath()+"userpower/sysRole/list",
			$scope.request).then(function(res) { // 调用承诺API获取数据 .resolve
				if (res.success) {
					$scope.sysRoleList = res.data;
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
			$httpRequest.post($ctx.getWebPath() + "userpower/sysRole/delete/"+$scope.sysRoleList[index].id).then(function(res) {
				  		if(res.success){
				  			$jBoxcm.success("删除数据成功");
				  			$state.reload($scope.currentRouteName);
				  		} else {
				  			$jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};

		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "userpower/sysRole/save",
					$scope.updateSysRole).then(function(res) {
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
			
			angular.copy($scope.sysRoleList[index], $scope.updateSysRole);
			$httpRequest.post($ctx.getWebPath() + "userpower/sysRole/getTree/"+$scope.updateSysRole.id).then(function(res) {
		                  if(res.success){
		                	    $scope.updateSysRole.sysPowerIds = [];
		                	    for(var i in res.data.sysPowerList){
		                	    	if(res.data.sysPowerList[i].checked){
		                	    		$scope.updateSysRole.sysPowerIds.push(res.data.sysPowerList[i].id);
		                	    	}
		                	    }
		                	    var setting = $zTreecm.setting({
		                	    	treeId : "updateTree",
		                	        onCheck: function(e,treeId,treeNode){
								        var treeObj=$.fn.zTree.getZTreeObj("updateTree");
								        nodes=treeObj.getCheckedNodes(true);
								        $scope.updateSysRole.sysPowerIds = [];
								        for(var i=0;i<nodes.length;i++){
								             $scope.updateSysRole.sysPowerIds.push(nodes[i].id);
								             $scope.initParentNodes(nodes[i],nodes,$scope.updateSysRole.sysPowerIds);
								        }
								    }
		                	    });
							    $.fn.zTree.init( $("#updateTree"), setting, res.data.sysPowerList);
		                		$('#updateModal').modal('show');
		                  } else {
		                	  $jBoxcm.error("修改数据错误,"+res.errorMessage);
		                  }
			});
		
		}
		
		$scope.initParentNodes = function(node,nodes,data){
		    var parentNode =	node.getParentNode();
		    var flag = true;
            if(parentNode != undefined && parentNode != null){
		    	for(var i=0;i<nodes.length;i++){
		    		if(parentNode.id == nodes[i].id){
		    			flag = false;
		    		}
		    	}
		    	if(flag) {
		    		data.push(parentNode.id);
		    		parentNode.checked = true;
		    		$scope.initParentNodes(parentNode,nodes,data);
		    	}
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
			$scope.updateSysRole = {};
			$scope.sysRole = {sysPowerList:[]};
			$page.init($scope, $page.getPageSize());
			$('#top-tab a[href="#sysRoleFormTab"]').on('shown.bs.tab', function(e){
				$scope.queryAllTree();
		    });
		}
		
		$scope.queryAllTree = function (){
			$httpRequest.post($ctx.getWebPath()+"userpower/sysRole/getAllTree",
					 {}).then(function(res) { // 调用承诺API获取数据
						if (res.success) {
	                	    var setting = $zTreecm.setting({
	                	    	treeId : "tree",
	                	        onCheck: function(e,treeId,treeNode){
	                	        	 var treeObj=$.fn.zTree.getZTreeObj("tree"),
						             nodes=treeObj.getCheckedNodes(true);
						        	 $scope.sysRole.sysPowerIds = [];
						             for(var i=0;i<nodes.length;i++){
						            	 $scope.sysRole.sysPowerIds.push(nodes[i].id);
						            	 $scope.initParentNodes(nodes[i],nodes,$scope.updateSysRole.sysPowerIds);
						             }
							    }
	                	    });
						    $.fn.zTree.init( $("#tree"), setting, res.data);
						} else {
							$jBoxcm.error("查询数据失败,"+res.errorMessage);
						}
					});
		}
		
		
	};
});