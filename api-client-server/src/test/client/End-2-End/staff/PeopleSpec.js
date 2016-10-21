describe('Staff people page', function() {

    beforeEach(function () {
        browser.get('/#/staff/people');
        waitUrl('/#/staff/people');
    });

    function waitUrl(urlInput) {
        browser.wait(function() {
            return browser.getCurrentUrl().then(function(url) {
                return (url.indexOf(browser.baseUrl +  urlInput) !== -1);
            });
        });
    }

    function equalValueElement(el, text) {
        browser.wait(protractor.ExpectedConditions.textToBePresentInElement(el, text), 10000);
    }

    function checkAvailableElement(el) {
        browser.wait(protractor.ExpectedConditions.presenceOf(el), 10000);
    }

    it('When superUser tap "+ Add a user", open new page whe superUser may add new user', function() {
        element(by.cssContainingText('.md-button-add', '+ Add a user')).click();
        waitUrl('/#/user/superAdmin');
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/user/superAdmin');

        element(by.model('email')).sendKeys('TestEmailForProtractor@example.com');
        element(by.id('singleSelect')).click();
        element(by.css('select option:nth-child(2)')).click();

        expect(element(by.model('type')).getAttribute('value')).toEqual('EMPLOYEE');
        element(by.cssContainingText('.md-primary', 'Send')).click();

        equalValueElement(element(by.binding('successfullySend')), "Message sent successfully to TestEmailForProtractor@example.com");
        expect(element(by.binding('successfullySend')).getText()).toBe("Message sent successfully to TestEmailForProtractor@example.com");
    });

    it('Edit new user for write name and last name', function() {
        browser.get('/#/user/create/1');
        waitUrl('/#/user/create/1');
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/user/create/1');

        element(by.model('user.lastName')).sendKeys('lastNameUser');
        expect(element(by.model('user.lastName')).getAttribute('value')).toEqual('lastNameUser');

        element(by.cssContainingText('.md-primary', 'save')).click();
        waitUrl('/#/');
        browser.get('/#/staff/people');
        browser.get('/#/staff/people');
        waitUrl('/#/staff/people');
    });

    it('When user searching users in search, on the page must filter all user by search value', function() {
        checkAvailableElement(element(by.repeater('user in users')));
        checkAvailableElement(element(by.model('searchText')));

        element(by.model('searchText')).sendKeys('TestEmailForProtractor');

        equalValueElement(element(by.repeater('user in users').column('user.email')), "TestEmailForProtractor@example.com");
        expect(element.all(by.repeater('user in users').column('user.email')).getText()).toEqual(['TestEmailForProtractor@example.com']);
        element(by.model('searchText')).clear();
    });


    it('When user open staff-people, he must sees list all users', function() {
        checkAvailableElement(element(by.repeater('user in users')));

        expect(element.all(by.repeater('user in users')).count()).toEqual(1);
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/staff/people');
    });

    it('When user click on other user, and after should be open user page', function () {
        checkAvailableElement(element(by.repeater('user in users')));
        element(by.repeater('user in users').row(0)).click();

        waitUrl('/#/user/show/1');
        checkAvailableElement(element(by.css('.info-user-text')));
        expect(element.all(by.css('.info-user-text')).getText()).toEqual(['TestEmailForProtractor@example.com', 'null', 'null']);
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/user/show/1');
    });

    it('When user click select departments on header(locate on staff page),after his must should be open page departments', function () {
        browser.findElement(by.name('departments')).click();
        waitUrl('/#/staff/departments');
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/staff/departments');
    });

    it('When user click select vacancy on header(locate on staff page),after his must should be open page vacancy', function () {
        browser.findElement(by.name('vacancy')).click();
        waitUrl('/#/staff/vacancy');
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/staff/vacancy');
    });

});