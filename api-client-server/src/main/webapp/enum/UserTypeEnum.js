/**
 * Enum for Users Type
 * Static data types for the user in order to distinguish users
 *
 * @author Andrii Blyznuk
 */

app
    .factory('UserTypeEnum', function() {
        return {
            EMPLOYEE: 'EMPLOYEE',
            CUSTOMER: 'CUSTOMER',
            RELEASED: 'RELEASED',
            CANDIDATE: 'CANDIDATE'
        }
    });



