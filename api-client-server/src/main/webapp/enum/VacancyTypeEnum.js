/**
 * Enum for Vacancy Type
 * Static data types for the vacancy in order to distinguish vacancies
 *
 * @author Andrii Blyznuk
 */

app
    .factory('VacancyTypeEnum', function() {
        return {
            OPENED: 'OPENED',
            INACTIVE: 'INACTIVE',
            CLOSED: 'CLOSED'
        }
    });