"use strict";

angular.module('sprintifier')
	.controller('ProjectDetailCtrl', [
	
		'$scope',
		'$state',
		'ProjectFactory',
	
		function($scope, $state, ProjectFactory, TeamMemberFactory) {
			
			$scope.projectId = $state.params.id;
			$scope.project = {};

			$scope.getById = function() {
				ProjectFactory.getWithMembers($scope.projectId)
					.then(function(responce) {
						$scope.project = responce.data.data;
					}
				);
			};

			$scope.removeMember = function(id) {
				TeamMemberFactory.del(id);
			};

			function init() {
				$scope.getById();
			};
			init();
		}
	
	]);