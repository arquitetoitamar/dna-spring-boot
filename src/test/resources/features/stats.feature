Feature: Stats functionalities
#  @TagPost
#  Scenario Outline: Verify if dna sequence is simian
#    Given the client send a sequence <dna>
#    When the api response should be <simian>
#    Examples:
#      | dna                           | simian |
#      | "CTGAGA,CTATGC,TATTGT,CCCCTA" | "true" |

#  @IsSimianJson
#  Scenario Outline: Verify if dna sequence is simian
#    Given the client send a payload
#    """json
#    {
#      "dna": ["CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"]
#    }
#    """