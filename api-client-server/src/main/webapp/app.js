/**
 * Angular application entry point
 * Defines ERP module and add configuration for parsing all jsog
 *
 * @author Dmitry Sheremet
 */
var app = angular
    .module('ERP', ['ngMaterial', 'ui.router', 'ngFileUpload', 'ngMdIcons', angularDragula(angular), 'angularMoment', "ngCookies"])
    .config(function ($httpProvider, $mdThemingProvider, $mdDateLocaleProvider) {

        $httpProvider.interceptors.push('HttpRequestInterceptor');

        $mdThemingProvider.theme('docs-dark', 'default')
            .dark();
        $mdThemingProvider.theme('dark-blue').backgroundPalette('light-blue').dark();
		$mdDateLocaleProvider.formatDate = function(date) {
            return date ? moment(date).format('DD-MM-YYYY') : '';
        };

        $mdDateLocaleProvider.parseDate = function(dateString) {
            var m = moment(dateString, 'YYYY-MM-DD', true);
            return m.isValid() ? m.toDate() : new Date(NaN);
        };
    }).constant("formats", {
        date: "YYYY-MM-DD"
    });