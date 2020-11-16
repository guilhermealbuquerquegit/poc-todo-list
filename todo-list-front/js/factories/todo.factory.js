(function () {
    'use strict';

    angular
        .module('TodoList')
        .factory('ApplicationService', ApplicationService);

    function ApplicationService($resource) {

        return {
            getAll: getAll,
            saveTodo: saveTodo,
            deleteTodo: deleteTodo,
            updateTodo: updateTodo
        };

        function getAll() {
            return resource().findAll().$promise;
        }


        function updateTodo(todo) {
            return resource().update({id: todo.id}, todo).$promise;
        }

        function saveTodo(Todo) {
            return resource().save(Todo).$promise;
        }

        function deleteTodo(id) {
            return resource().delete({id}).$promise;
        }

        function resource() {
            return $resource('http://localhost:8080/poc/api/todos/:id' , {
                id: '@id',
            }, {
                findAll: {
                    method: 'GET',
                    isArray: true
                },
                save: {
                    method: 'POST',
                    interceptor: {
                        response: function (response) {
                            response.resource.$httpHeaders = response.headers;
                            return response.resource;
                        }
                    }
                },
                delete: {
                    method: 'DELETE',
                },
                update: {
                    method: 'PUT'
                }
            }
            );



        }
    }
})();