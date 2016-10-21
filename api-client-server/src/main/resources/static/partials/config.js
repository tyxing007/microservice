ssta.config(function ($httpProvider) {
    $httpProvider.interceptors.push('HttpRequestInterceptor');
});