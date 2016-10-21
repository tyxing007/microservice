/**
 * Enum for Candidate Type
 * Static data types for the user in order to distinguish users
 *
 * @author Andrii Blyznuk
 */

app
    .factory('CandidateTypeEnum', function() {
        return {
            TAKEN: 'TAKEN',
            PENDING: 'PENDING',
            REJECTED: 'REJECTED'
        }
    });

