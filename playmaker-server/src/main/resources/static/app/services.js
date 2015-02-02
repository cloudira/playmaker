(function(angular) {
	var ServiceFactory = function($resource) {
		return $resource('/api/services/:id', { id: '@id' }, {
			update: {
				method: "PUT"
			}
		});
	};
	
	var ServiceInstanceFactory = function($resource) {
		return $resource('/api/instances/:id', { id: '@id' }, {
			details: {
				method: 'GET', 
				url: '/api/instances/:id/details'
			},
			env: {
				method: 'GET',
				url: 'api/instances/:id/env',
				isArray: true
			},
			beans: {
				method: 'GET', 
				url: '/api/instances/:id/beans'
			}
		});
	};
	
	var ProfileFactory = function($resource) {
		return $resource('/api/profiles/:id', { id: '@id' }, {
			update: {
				method: "PUT"
			}
		});
	};

	ServiceFactory.$inject = ['$resource'];
	ServiceInstanceFactory.$inject = ['$resource'];
	ProfileFactory.$inject = ['$resource'];
	
	angular.module("playmaker.services")
		.factory("Service", ServiceFactory)
		.factory("ServiceInstance", ServiceInstanceFactory)
		.factory("Profile", ProfileFactory);
	
}(angular));