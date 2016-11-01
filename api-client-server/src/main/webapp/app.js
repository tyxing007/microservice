/**
 * Angular application entry point
 * Defines ERP module and add configuration for parsing all jsog
 *
 * @author Dmitry Sheremet
 */
var app = angular
    .module('ERP', ['ui.bootstrap', 'toggle-switch', 'ui.router', 'ngFileUpload', 'ngMdIcons', angularDragula(angular), 'angularMoment'])
    .config(function ($httpProvider) {

    }).constant("formats", {
        date: "YYYY-MM-DD"
    });