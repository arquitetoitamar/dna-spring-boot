package com.mercadolivre.dna.service.impl;

import com.mercadolivre.dna.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  calculator service impl
 *
 */
@Slf4j
@Service
public class CalculatorServiceImpl implements
        CalculatorService {

    /**
     * Rows and columns in the given grid
     */
    private static int R = 6;
    /**
     *  Rows and columns in the given grid
     */
    private static int C = 6;

    /**
     *
     *  For searching in all 8 direction
     */
    private static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    /**
     * adjacents types
     * Types to match two times to identify simian
     */
    private static String[] adjacentsTypes = { "AAAA","CCCC","TTTT","GGGG" };
    /**
     * verify type dna
     *  Complexity Analysis:
     *
     *      Time complexity: O(R*C*8*len(str)).
     *      All the cells will be visited and traversed in all 8 directions, where R and C is side of matrix so time complexity is O(R*C).
     *      Auxiliary Space: O(1).
     *      As no extra space is needed.
     * @param sequence 序列
     * @return {@link boolean}
     */
    @Override
    public boolean verifyTypeDna(List<String> sequence) {
        log.info("verifying TypeDna");
        List<char[]> collect = sequence.stream()
                .map(String::toCharArray)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        char[][] chars = collect.toArray(char[][]::new);
        for (String adjacentType : adjacentsTypes) {
            if(patternSearch(chars, adjacentType)){
                return true;
            }
        }
        return false;
    }

    /**
     * pattern search ex.: AAAA
     *  Searches given word in a given
     *     matrix in all 8 directions
     *
     *      Consider every point as starting
     *         point and search given word
     * @param grid char[][]
     * @param word String
     * @param count int
     */
    private boolean patternSearch(
            char[][] grid,
            String word)
    {
        int matchCount = 0;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (search2dArray(grid, row, col, word)) {
                    matchCount++;
                    log.debug(  "{} {} pattern found at row {} and col {}",word,matchCount, row, col);
                    if(matchCount == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * search2d array
     *      This function searches in all
     *      8-direction from point
     *      (row, col) in grid[][]
     *
     *      If first character of word
     *         doesn't match with
     *         given starting point in grid.
     * @param grid char[][]
     * @param row int
     * @param col col
     * @param word String
     * @return {@link boolean}
     */
    private boolean search2dArray(char[][] grid, int row,
                                         int col, String word)
    {
        if (grid[row][col] != word.charAt(0))
            return false;
        int len = word.length();
        // Search word in all 8 directions
        // starting from (row, col)
        for (int dir = 0; dir < 8; dir++) {
            // Initialize starting point
            // for current direction
            int k, rd = row + x[dir], cd = col + y[dir];

            // First character is already checked,
            // match remaining characters
            for (k = 1; k < len; k++) {
                // If out of bound break
                if (rd >= R || rd < 0 || cd >= C || cd < 0)
                    break;
                // If not matched, break
                if (grid[rd][cd] != word.charAt(k))
                    break;
                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }
            // If all character matched,
            // then value of must
            // be equal to length of word
            if (k == len)
                return true;
        }
        return false;
    }
}

