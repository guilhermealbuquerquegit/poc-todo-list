(function () {
    'use strict';

    angular
        .module('TodoList')
        .controller('TodoListCtrl', TodoListCtrl);

    function TodoListCtrl(ApplicationService) {

        var vm = this;
        vm.init = init;
        vm.findApplications = findApplications;
        vm.getTotalTodos = getTotalTodos;
        vm.addTodo = addTodo;
        vm.removeTodo = removeTodo;
        vm.alterCheck = alterCheck;


        vm.todos = [];
        init();

        function init() {
            findApplications();
        }

        function findApplications() {
            ApplicationService.getAll().then(function success(applications) {
                vm.todos = applications;
            }, function error(exception) {
                exception.data;
            });
        }

        function getTotalTodos() {
            vm.todos.sort((a, b) => {
                return a.done - b.done;
            })
            return vm.todos.length;
        }

        function alterCheck(todo) {
            todo.done = !todo.done;
            ApplicationService.updateTodo(todo).then(function success(applications) {
                swal("Atividade Concluída!.", "", "success");
                findApplications
            }, function error(exception) {
                swal(exception.data, "", "error");
            })
        }

        function addTodo() {
            ApplicationService.saveTodo({ nome: vm.formTodoText, done: false }).then(function success(applications) {
                findApplications();
                swal("Atividade criada.", "", "success");
            }, function error(exception) {
                swal(exception.data, "", "error");
            })
            vm.formTodoText = '';
        }

        function removeTodo(todo) {
            swal({
                title: "Realmente deseja remover esta atividade ?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        ApplicationService.deleteTodo(todo.id).then(function success(applications) {
                            findApplications();
                            swal("Atividade removida com sucesso.", {
                                icon: "success",
                            });
                        })
                    } else {
                        function error(exception) {
                            swal(exception.data, "", "error");
                        }
                    }
                });
        }


    }
})();