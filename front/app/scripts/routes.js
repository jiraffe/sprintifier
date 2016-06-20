'use strict';

app.config([

	'$urlRouterProvider',
	'$stateProvider',
	'$locationProvider',
	
	function($urlRouterProvider, $stateProvider, $locationProvider) {
	
		$urlRouterProvider.otherwise('/');
		if(navigator.appVersion.indexOf("MSIE 8.") === -1 && navigator.appVersion.indexOf("MSIE 9.") === -1){
			$locationProvider.html5Mode(true);
		}
	
		$stateProvider.state('base', {

			templateUrl: 'views/templates/main.html',
			controller: 'MainController',
			controllerAs: 'ctrl'
		})
		.state('index', {
			parent: 'base',
			url: '/',
			templateUrl: 'views/pages/index.html',
			data: {
				title: 'Index'
			}
		})
		.state('projects', {
			parent: 'base',
			url: '/projects',
			templateUrl: 'views/pages/project/projects.html',
			controller: 'ProjectCtrl',
			data: {
				title: 'Projects'
			}
		})
		.state('projectDetail', {
			parent: 'base',
			url: '/project/:id',
			templateUrl: 'views/pages/project/projectDetail.html',
			controller: 'ProjectDetailCtrl',
			data: {
				title: 'Project info'
			}
		})
		.state('users', {
			parent: 'base',
			url: '/users',
			templateUrl: 'views/pages/users.html',
			controller: 'UserCtrl',
			data: {
				title: 'Users'
			}
		})
		.state('dayoffs', {
			parent: 'base',
			url: '/dayoffs',
			templateUrl: 'views/pages/dayoffs.html',
			controller: 'DayoffCtrl',
			data: {
				title: 'Dayoffs'
			}
		});
		
	}]
)