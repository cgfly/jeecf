define([ 'app', '$httpRequest', '$page', '$ctx', '$jBoxcm' ], function(app,
		$httpRequest,$state, $page, $ctx, $jBoxcm) {
	return function($scope, $rootScope, $httpRequest,$state, $page, $ctx, $jBoxcm) {

		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath() + "config/sysMenu/save",
					$scope.sysMenu).then(function(res) {
		                  if(res.success){
		                	  $jBoxcm.success("保存数据成功");
		                	  $state.reload($scope.currentRouteName);
		                  } else {
		                	  $jBoxcm.error("保存数据失败,"+res.errorMessage);
		                  }
			});
		};

		$scope.searchForm = function() {
			
			$httpRequest.post($ctx.getWebPath() + "config/sysMenu/list", 
			$scope.request).then(function(res) {
			        if (res.success) {
						var data = res.data;
						$scope.sysMenuList = data;
						$page.setPage($scope, 0);
						setTimeout(function(){
							$jBoxcm.delConfrim($scope);
						},800);
					} else {
						$jBoxcm.error("查询数据失败,"+res.errorMessage);
					}
			});
		};
		
		$scope.deleteForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysMenu/delete/"+$scope.sysMenuList[index].id).then(function(res) {
				  		if(res.success){
				  			 $jBoxcm.success("删除数据成功");
				  			 $state.reload($scope.currentRouteName);
				  		} else {
				  			 $jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};
		
		$scope.updateForm = function(){
			$httpRequest.post($ctx.getWebPath() + "config/sysMenu/save",
					$scope.updateSysMenu).then(function(res) {
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
		
		$scope.addModal = function (index){
			$scope.sysMenu.label = undefined;
			$scope.sysMenu.name = undefined;
			$scope.sysMenu.permission = undefined;
			$scope.sysMenu.isIcon = $scope.sysMenuList[index].isIcon;
			$scope.sysMenu.route = $scope.sysMenuList[index].route+"";
			$scope.sysMenu.moduleLabel = $scope.sysMenuList[index].moduleLabel;
			$scope.sysMenu.parentId = $scope.sysMenuList[index].id;
		    $('#sysMenuFormTab #insertTreeId').find('input[name="selectedName"]').val($scope.sysMenuList[index].name);
			$scope.sysMenu.sort = parseInt($scope.sysMenuList[index].sort)+10;
			$('#top-tab a[href="#sysMenuFormTab"]').tab('show');
		}
		
		$scope.updateModal = function(index) {
			$('#updateModal #updateTreeId').find('input[name="selectedName"]').val(null);
			$('#updateModal').modal('show');
			angular.copy($scope.sysMenuList[index], $scope.updateSysMenu);
			if($scope.updateSysMenu.parent != null){
			   $('#updateModal #updateTreeId').find('input[name="selectedName"]').val($scope.updateSysMenu.parent.name);
			}
		}

		$scope.initPageBack = function(request) {
			$scope.request.page = null;
			return $scope.searchForm();
		};

		this.init = function() {
			
			$scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {page:{current:"",size:""},data:{}};
			$scope.sysMenu = {route:"0",isIcon:0};
			$scope.updateSysMenu = {route:"0"};
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
				   $jBoxcm.treeTable($scope, this, $scope.sysMenu);
			   } else if(id == "updateTreeId"){
				   $jBoxcm.treeTable($scope, this, $scope.updateSysMenu);
			   }
			});
		}

		$scope.renderFinish = function(e) {
			 $("#sysMenuListTreeTable").treetable({
				expandable : true
			 },true);
		}
		
		$scope.insertLabelChange = function(nv, ov) {
			$scope.labelChange($scope.sysMenu);
		}
		
		$scope.updateLabelChange = function(nv, ov) {
			$scope.labelChange($scope.updateSysMenu);
		}
		
		$scope.insertModuleLabelChange = function(nv, ov) {
			$scope.moduleLabelChange($scope.sysMenu);
		}
		
		$scope.updateModuleLabelChange = function(nv, ov) {
			$scope.moduleLabelChange($scope.updateSysMenu);
		}
		

		$scope.labelChange = function(nv) {
		    var moduleLabel = nv.moduleLabel;
		    var level = nv.level;
		    var label = nv.label;
			if(level == 1 || moduleLabel == undefined){
				if(label != undefined && label != ""){
					nv.permission = label+":view";
				}
			} else if (label == undefined ||  label == "") {
				nv.permission = moduleLabel+":view";
			} else {
				nv.permission = moduleLabel+":"+label+":view";
			}
			
		}
		
		$scope.moduleLabelChange = function(nv) {
			var label = nv.label;
			var moduleLabel = nv.moduleLabel;
			if(moduleLabel == undefined || moduleLabel == "" || moduleLabel == label){
				if (label != undefined && label != "") {
					nv.permission = label+":view";
				} 
			} else if (label == undefined || label == "") {
				nv.permission = moduleLabel+":view";
			} else {
				nv.permission = moduleLabel+":"+label+":view";
			}
		}
	};
});