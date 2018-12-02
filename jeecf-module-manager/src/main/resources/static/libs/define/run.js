define([ 'app', 'route' ], function(app) {
	app.run([ '$rootScope', '$transitions', function($rootScope, $transitions) {
		$transitions.onSuccess({}, function(trans) {
			$(document).ready(function() {
				$("select.select2").each(function() {
					$(this).select2();
				});
			});
		});
	} ]);

});