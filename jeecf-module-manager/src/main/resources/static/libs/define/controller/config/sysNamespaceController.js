define([ 'app', '$httpRequest', '$page', '$ctx', '$jBoxcm' ], function(app,
		$httpRequest, $state, $page, $ctx, $jBoxcm) {
	return function($scope, $rootScope, $httpRequest, $state, $page, $ctx,
			$jBoxcm) {

		$scope.submitForm = function() {
			$httpRequest.post($ctx.getWebPath() + "config/sysNamespace/save",
					$scope.sysNamespace).then(function(res) {
				if (res.success) {
					$jBoxcm.success("保存数据成功");
					$state.reload($scope.currentRouteName);
				} else {
					$jBoxcm.error("保存数据失败," + res.errorMessage);
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
			
			$httpRequest.post($ctx.getWebPath() + "config/sysNamespace/list",
					$scope.request).then(function(res) { // 调用承诺API获取数据
															// .resolve
				if (res.success) {
					$scope.sysNamespaceList = res.data;
					$page.setPage($scope, res.page.total);
					setTimeout(function(){
						$jBoxcm.delConfrim($scope);
					},800);
				} else {
					$jBoxcm.error("查询数据失败," + res.errorMessage);
				}
			});
		};

		$scope.deleteForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysNamespace/delete/"+$scope.sysNamespaceList[index].id).then(function(res) {
				if (res.success) {
					$jBoxcm.success("删除数据成功");
					$state.reload($scope.currentRouteName);
				} else {
					$jBoxcm.error("删除数据失败," + res.errorMessage);
				}
			});
		};

		$scope.updateForm = function() {
			$httpRequest.post($ctx.getWebPath() + "config/sysNamespace/save",
					$scope.updateSysNamespace).then(function(res) {
				if (res.success) {
					$('#updateModal').modal('hide');
					setTimeout(function() {
						$state.reload($scope.currentRouteName);
						$jBoxcm.success("修改数据成功");
					}, 500);
				} else {
					$jBoxcm.error("修改数据失败," + res.errorMessage);
				}
			});
		}

		$scope.updateModal = function(index) {
			$('#updateModal').modal('show');
			angular.copy($scope.sysNamespaceList[index],
					$scope.updateSysNamespace);
		}

		$scope.initPageBack = function(request) {
			if ($scope.query == undefined || $scope.query.page == undefined) {
				$scope.request.page.current = request.page.current;
			} else {
				$scope.request.page.current = $scope.query.page;
			}
			$scope.request.page.size = request.page.size;
			return $scope.searchForm();
		};

		this.init = function() {
			$scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.request = {
				page : {
					current : "",
					size : ""
				},
				data : {}
			};
			$scope.updateSysNamespace = {};
			$page.init($scope, $page.getPageSize());
		}

		$scope.effectForm = function(index) {
			$httpRequest.post($ctx.getWebPath() + "config/sysNamespace/effect/"+
					$scope.sysNamespaceList[index].id).then(function(res) {
				if (res.success) {
					$state.reload($scope.currentRouteName);
					$jBoxcm.success("操作成功");
				} else {
					$jBoxcm.error("操作失败," + res.errorMessage);
				}
			});
		}
		

	};
});