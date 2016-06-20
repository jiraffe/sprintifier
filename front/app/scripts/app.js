var app = angular.module('sprintifier', [ 
	'ui.router',
	'ui.bootstrap',
	'ui.utils']);

app.config([

		'$httpProvider',
    	'$animateProvider',

		function($httpProvider, $animateProvider) {
			
	        var requestInterceptor = function($q){
	            return {
	                'request': function (config) {
						
						if(config.url.indexOf('.') === -1)	{
                    		config.url = 'http://127.0.0.1:8080/sprintifier-api' + config.url;
	                	}

	                    return config || $q.when(config);
	                }
	            }
	        };

	        $httpProvider.interceptors.push(requestInterceptor);
		}

	]);

app.run(function($rootScope, $state) {
	$rootScope.$state = $state;

	$rootScope.$on('$stateChangeSuccess', function(e, toState) {
		if (toState.data) {
			document.title = toState.data.title;
		}
		$rootScope.pageTitle = toState.data.title;
		$rootScope.pageName = toState.name;
	});
	
});