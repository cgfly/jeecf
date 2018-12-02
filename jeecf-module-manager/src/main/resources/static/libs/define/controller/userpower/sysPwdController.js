define([ 'app', '$httpRequest', '$page', '$ctx', '$jBoxcm' ], function(app,
		$httpRequest, $state, $page, $ctx, $jBoxcm) {
	return function($scope, $rootScope, $httpRequest, $state, $page, $ctx,
			$jBoxcm) {

		$scope.submitForm = function() {
			var sysPwd = $scope.sysPwd;
			if (sysPwd.newPassword == sysPwd.confirmPassword) {
				$httpRequest.post($ctx.getWebPath() + "userPower/sysPwd/save",
						$scope.sysPwd).then(function(res) {
					if (res.success) {
						$jBoxcm.success("保存数据成功");
						$state.reload($scope.currentRouteName);
					} else {
						$jBoxcm.error("保存数据失败," + res.errorMessage);
					}
				});
			} else {
				$jBoxcm.error("保存数据失败,新密码与确认密码不一致");
			}
		};

		this.init = function() {
			$scope.currentRouteName = $state.current.name;
			$scope.currentRouteUrl = $state.current.url;
			$scope.sysPwd = {};
		}

	};
});