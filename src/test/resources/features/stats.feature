Feature: Stats functionalities
  @TagPost
  Scenario Outline: Verify if dna sequence is simian
    Given the client send a sequence <dna>
    Examples:
      | dna                                  | simian |
      | CTGAGACTATGCTATTGTAGAGGGCCCCTATCACTG | "true" |

  @Simian
  Scenario Outline: Verify if dna sequence is simian
    Given the client send a payload
    """json
    {
      "dna": ["CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"]
    }
    """