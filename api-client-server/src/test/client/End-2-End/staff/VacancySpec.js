describe('Staff vacancy page', function() {

    beforeEach(function () {
        browser.get('/#/staff/vacancy');
        waitUrl('/#/staff/vacancy');
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



    it('When superUser tap "Create a vacancy", open popup whe superUser may create new vacancy', function() {
        checkAvailableElement(element(by.css('.md-button-add')));
        element(by.cssContainingText('.md-button-add', 'Create a vacancy')).click();

        element(by.model('newVacancy.name')).sendKeys('Test Vacancy');
        expect(element(by.model('newVacancy.name')).getAttribute('value')).toBe('Test Vacancy');

        //
        // browser.wait(function() {
        //     browser.get('/#/staff/vacancy');
        //     waitUrl('/#/staff/vacancy');
        //
        //     checkAvailableElement(element(by.css('.md-button-add')));
        //     element(by.cssContainingText('.md-button-add', 'Create a vacancy')).click();
        //
        //     element(by.model('newVacancy.name')).sendKeys('Test Vacancy');
        //     expect(element(by.model('newVacancy.name')).getAttribute('value')).toBe('Test Vacancy');
        //
        //     element(by.cssContainingText('.select-in-modal-create-vacancy', 'Select type')).click();
        //     expect(element.all(by.repeater('types in typeVacancy')).count()).toBe(3);
        //
        //     checkAvailableElement(element(by.cssContainingText('md-option', 'OPENED')));
        //     element(by.cssContainingText('md-option', 'OPENED')).click();
        //
        //     element(by.cssContainingText('.md-checkbox-for-create-vacancy', 'Make hot job')).click();
        //
        //     element(by.cssContainingText('.md-input-container-responsible', 'Select responsible')).click();
        //     checkAvailableElement(element(by.repeater('responsible in $root.autocomplete.responsible')));
        //     expect(element.all(by.repeater('responsible in $root.autocomplete.responsible')).count()).toBe(1);
        //
        //     checkAvailableElement(element(by.cssContainingText('md-option', 'null lastNameUser')));
        //     element(by.cssContainingText('md-option', 'null lastNameUser')).click();
        //
        //     element(by.buttonText('Save')).click();
        // }, 1000000);

        element(by.cssContainingText('.select-in-modal-create-vacancy', 'Select type')).click();
        expect(element.all(by.repeater('types in typeVacancy')).count()).toBe(3);

        checkAvailableElement(element(by.cssContainingText('md-option', 'OPENED')));
        element(by.cssContainingText('md-option', 'OPENED')).click();

        element(by.cssContainingText('.md-checkbox-for-create-vacancy', 'Make hot job')).click();

        element(by.cssContainingText('.md-input-container-responsible', 'Select responsible')).click();
        checkAvailableElement(element(by.repeater('responsible in $root.autocomplete.responsible')));
        expect(element.all(by.repeater('responsible in $root.autocomplete.responsible')).count()).toBe(1);

        checkAvailableElement(element(by.cssContainingText('md-option', 'null lastNameUser')));
        element(by.cssContainingText('md-option', 'null lastNameUser')).click();

        element(by.buttonText('Save')).click();
        browser.get('/#/staff/vacancy');
    });

    it('When user open staff-vacancy, he must sees list all vacancies', function() {
        checkAvailableElement(element(by.repeater('vacancy in vacancies')));
        expect(element.all(by.repeater('vacancy in vacancies')).count()).toEqual(1);
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/#/staff/vacancy');
    });

    it('When user searching vacancy in search, on the page must filter all vacancy by search value', function() {
        checkAvailableElement(element(by.model('searchText')));
        element(by.model('searchText')).sendKeys('Test');

        checkAvailableElement(element(by.repeater('vacancy in vacancies')));
        expect(element.all(by.repeater('vacancy in vacancies').column('vacancy.name')).getText()).toEqual(['Test Vacancy']);
        element(by.model('searchText')).clear();
    });

    it('When superUser tap "Add participant", open popup whe superUser may add candidate', function () {
        checkAvailableElement(element(by.repeater('vacancy in vacancies')));
        var vacancy = element(by.repeater('vacancy in vacancies').row(0));
        vacancy.element(by.css('.div-for-add-flex-vacancy .height-for-span-text')).click();

        element(by.model('newCandidate.email')).sendKeys('cadidateEmail@test.te');
        expect(element(by.model('newCandidate.email')).getAttribute('value')).toBe('cadidateEmail@test.te');

        element(by.model('newCandidate.name')).sendKeys('candidateName');
        expect(element(by.model('newCandidate.name')).getAttribute('value')).toBe('candidateName');

        element(by.model('newCandidate.lastName')).sendKeys('candidateLastName');
        expect(element(by.model('newCandidate.lastName')).getAttribute('value')).toBe('candidateLastName');

        element(by.buttonText('Save')).click();
        waitUrl('/#/staff/vacancy');
    });


    it('When superUser select vacancy and click on her, his must sees dropDown menu with candidates and steps, ' +
        'and when superUser click on step his must sees popup where his can create step', function() {

        checkAvailableElement(element(by.repeater('vacancy in vacancies')));
        var vacancy = element(by.repeater('vacancy in vacancies').row(0));

        checkAvailableElement(element(by.css('.div-for-more-vacancy')));
        vacancy.element(by.css('.div-for-more-vacancy')).click();

        checkAvailableElement(vacancy.element(by.repeater('candidate in vacancy.candidates')));
        var candidate = vacancy.element(by.repeater('candidate in vacancy.candidates').row(1));
        expect(candidate.element(by.binding('candidate.name')).getText()).toBe("candidateName candidateLastName");

        checkAvailableElement(candidate.element(by.repeater('step in candidate.stepToCandidate')));
        var step = candidate.element(by.repeater('step in candidate.stepToCandidate').row(0));
        expect(step.element(by.binding('step.gpaMark')).getText()).toBe('0');

        checkAvailableElement(step.element(by.css('.mark-for-step')));
        step.element(by.css('.mark-for-step')).click();

        checkAvailableElement(element(by.cssContainingText('.md-input-container-responsible', 'Select responsible')));
        element(by.cssContainingText('.md-input-container-responsible', 'Select responsible')).click();

        checkAvailableElement(element(by.repeater('responsible in $root.autocomplete.responsible')));
        expect(element.all(by.repeater('responsible in $root.autocomplete.responsible')).count()).toBe(1);

        checkAvailableElement(element(by.cssContainingText('md-option', 'null lastNameUser')));
        element(by.cssContainingText('md-option', 'null lastNameUser')).click();

        checkAvailableElement(element(by.css('.md-datepicker-expand-triangle')));
        element(by.css('.md-datepicker-expand-triangle')).click();

        checkAvailableElement(element(by.css('.md-calendar-date')));
        element(by.css('.md-calendar-date')).click();

        checkAvailableElement(element(by.buttonText('Create Interview')));
        element(by.buttonText('Create Interview')).click();
    });

    it('When user select vacancy and click on her, his must sees dropDown menu with candidates and steps, ' +
        'and when user click on step his must sees popup with information about this step', function() {
        checkAvailableElement(element(by.repeater('vacancy in vacancies')));
        var vacancy = element(by.repeater('vacancy in vacancies').row(0));

        checkAvailableElement(element(by.css('.div-for-more-vacancy')));
        vacancy.element(by.css('.div-for-more-vacancy')).click();

        checkAvailableElement(vacancy.element(by.repeater('candidate in vacancy.candidates')));
        var candidate = vacancy.element(by.repeater('candidate in vacancy.candidates').row(1));
        expect(candidate.element(by.binding('candidate.name')).getText()).toBe("candidateName candidateLastName");

        checkAvailableElement(candidate.element(by.repeater('step in candidate.stepToCandidate')));
        var step = candidate.element(by.repeater('step in candidate.stepToCandidate').row(0));
        expect(step.element(by.binding('step.gpaMark')).getText()).toBe('0');

        checkAvailableElement(step.element(by.css('.mark-for-step')));
        step.element(by.css('.mark-for-step')).click();

        expect(candidate.element(by.binding('candidate.name')).getText()).toBe("candidateName candidateLastName");
        expect(element(by.binding('stepInfo.candidate')).getText()).toBe("Interview with candidateName");
        expect(element(by.model('stepInfo.responsibleName')).getAttribute('value')).toBe("null lastNameUser");
    });


});