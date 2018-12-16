define([ 'app', '$httpRequest', '$page', '$ctx','$genHelper','$jBoxcm'], function(app, $httpRequest,
		$page, $ctx,$genHelper,$state,$jBoxcm) {
	return function($scope, $rootScope,$httpRequest, $page, $ctx,$genHelper,$state,$jBoxcm) {
		
		$scope.submitForm = function() {
			var tableColumns = JSON.parse(JSON.stringify($scope.tableColumn));  
			for(item in tableColumns){
				tableColumns[item].queryType = tableColumns[item].queryType.value;
				tableColumns[item].formType = tableColumns[item].formType.value;
				tableColumns[item].isKey = tableColumns[item].isKey.value;
			}
			$scope.table.genTableColumns = tableColumns;
			$httpRequest.post($ctx.getWebPath()+"template/genTable/save", $scope.table).then(
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
			
			$httpRequest.post($ctx.getWebPath() + "template/genTable/list",
				$scope.request).then(function(res) { // 调用承诺API获取数据 .resolve
				if (res.success) {
					$scope.genTableList = res.data;
					$page.setPage($scope, res.page.total);
					setTimeout(function(){
						$jBoxcm.delConfrim($scope);
					},800);
				} else {
					$jBoxcm.error("查询数据失败,"+res.errorMessage);
				}
			});
		};
		
		$scope.deleteForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "template/genTable/delete/"+$scope.genTableList[index].id).then(function(res) {
				  		if(res.success){
				  			 $jBoxcm.success("删除数据成功");
				  			 $state.reload($scope.currentRouteName);
				  		} else {
				  			 $jBoxcm.error("删除数据失败,"+res.errorMessage);
				  		}
			});
		};
		
		$scope.initPageBack = function(request) {
			if($scope.query == undefined || $scope.query.page == undefined){
				$scope.request.page.current = request.page.current;
			} else {
				$scope.request.page.current = $scope.query.page;
			}
			$scope.request.page.size = request.page.size;
			return $scope.searchForm(request);
		};

		this.init = function() {
			$scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {page:{current:"",size:""},data:{}};
			$scope.tableColumn = [];
			$scope.table = {};
			$page.init($scope, $page.getPageSize());
		}
        $('.queryBaseTableColumn').click(function(e) {
        	var name = $scope.selectedTable.name;
			$scope.table.className = $genHelper.toField(name);
			$scope.table.name = name;
        	$scope.queryBaseTableColumnList();
        });
		$('#top-tab a[href="#genTableForm"]').click(function(e) {
			$scope.queryBaseTableList();
		});

		$scope.queryBaseTableList = function() {
			$httpRequest.post("/template/genTable/queryBaseTableList").then(
					function(res) { // 调用承诺API获取数据 .resolve
						if(res.success){
							$scope.tableList = res.data;
						}
					});
		};
		
		$scope.queryBaseTableColumnList = function() { 
			$httpRequest.post("/template/genTable/queryBaseTableColumnList/"+$scope.table.name).then(
					function(res) { // 调用承诺API获取数据 .resolve
						$scope.isInsertCheck = undefined;
						$scope.isNullCheck = undefined;
						$scope.isEditCheck = undefined;
						$scope.isListCheck = undefined;
						$scope.isQueryCheck = undefined;
						
						$scope.table.comment = undefined;
						$scope.table.parentTable = undefined;
						$scope.table.parentTableFk = undefined;
						var  data =res.data;
						if(data[0].genTable != undefined){
							if(data[0].genTable.comment != undefined){
								$scope.table.comment = data[0].genTable.comment;
							}
							if(data[0].genTable.parentTable != undefined){
								$scope.table.parentTable = data[0].genTable.parentTable;
							}
							if(data[0].genTable.parentTableFk != undefined){
								$scope.table.parentTableFk = data[0].genTable.parentTableFk;
							}
						}
						for( i in data){
							var field = $genHelper.toField(data[i].name);
							var formType = $genHelper.toFormType(data[i].jdbcType);
							var queryTypes = $genHelper.getQueryTypes();
							var formTypes = $genHelper.getFormTypes();
							var isKey = $genHelper.isKey(data[i].isKey);
							$scope.queryTypes = queryTypes;
							$scope.formTypes = formTypes;
							if(data[i]["field"] == undefined){
								data[i]["field"] = field;
							}
							if(data[i]["queryType"] == undefined ){
								data[i]["queryType"] = queryTypes[0];
							} else {
								for(var key in queryTypes){
									if(queryTypes[key].value == data[i]["queryType"]){
										data[i]["queryType"] = queryTypes[key];
									}
								}
							}
							
							data[i]["formType"]  = formType;
							data[i]["isKey"] = isKey; 
						}
						$scope.tableColumn = res.data;
					});
		};
		$scope.isChecked = function(checked){
			if(checked  == 1){
				return true;
			}
			return false;
		}
		
		$scope.isNullChange = function(nv, ov){
			if($scope.isNullCheck != undefined && $scope.isNullCheck){
				var tableColumns = $scope.tableColumn;
		        for(var i in tableColumns){
		        	$scope.tableColumn[i].isNull = 1;
		        }
		    } else if($scope.isNullCheck != undefined && !$scope.isNullCheck){
		        var tableColumns = $scope.tableColumn;
		        for(var i in tableColumns){
		            $scope.tableColumn[i].isNull = 0;
		        }
		    }			
		}
		
		$scope.isInsertChange = function(nv, ov){
	        if($scope.isInsertCheck != undefined && $scope.isInsertCheck){
	            var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isInsert = 1;
	            }
	        } else if($scope.isInsertCheck != undefined && !$scope.isInsertCheck){
	        	var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isInsert = 0;
	            }
	        }
		}
		
		$scope.isEditChange = function(nv, ov){
	        if($scope.isEditCheck != undefined && $scope.isEditCheck){
	            var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isEdit = 1;
	            }
	        } else if($scope.isEditCheck != undefined && !$scope.isEditCheck){
	        	var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isEdit = 0;
	            }
	        }
		}
		
		$scope.isListChange = function(nv, ov){
	        if($scope.isListCheck != undefined && $scope.isListCheck){
	            var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isList = 1;
	            }
	        } else if($scope.isListCheck != undefined && !$scope.isListCheck){
	        	var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isList = 0;
	            }
	        }
		}
		
		$scope.isQueryChange = function(nv, ov){
	        if($scope.isQueryCheck != undefined && $scope.isQueryCheck){
	            var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isQuery = 1;
	            }
	        } else if($scope.isListCheck != undefined && !$scope.isQueryCheck){
	        	var tableColumns = $scope.tableColumn;
	            for(var i in tableColumns){
	            	$scope.tableColumn[i].isQuery = 0;
	            }
	        }
		}
		
		$scope.detailModal = function(index){
			$scope.detailTable = $scope.genTableList[index];
			$httpRequest.post("/template/genTable/queryBaseTableColumnList/"+$scope.detailTable.name).then(
					function(res) { 
						$scope.detailTable.columns = res.data;
						$('#detailModal').modal('show');
			});
			 
		}
		
	};
});