// exports.config = {
//     seleniumAddress: 'http://localhost:4444/wd/hub',
//     specs: ['src/test/e2e/*.js']
// };


exports.config = {

    specs: ['src/test/client/End-2-End/**/*.js'],

    capabilities: {
        browserName: 'chrome'
    },

    maxSessions: 1,


    baseUrl: 'http://localhost:8080',

    framework: 'jasmine2',

    onPrepare: function() {
        browser.ignoreSynchronization = true;
        browser.waitForAngular();
    },

    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 10000000,

    }
};
