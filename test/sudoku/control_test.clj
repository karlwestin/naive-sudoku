(ns sudoku.control-test
  (:require [sudoku.control :as control]
            [clojure.test :as test]))

(def valid-solution   [5 3 8 4 1 6 2 7 9 9 7 6 3 8 2 5 4 1 2 4 1 5 7 9 8 6 3 4 6 3 9 2 8 7 1 5 8 1 2 7 3 5 4 9 6 7 9 5 1 6 4 3 8 2 6 8 7 2 5 1 9 3 4 1 2 9 8 4 3 6 5 7 3 5 4 6 9 7 1 2 8])

(def invalid-solution [5 3 8 4 1 6 2 7 3 9 7 6 3 8 2 5 4 1 2 4 1 5 7 9 8 6 3 4 6 3 9 2 8 7 1 5 8 1 2 7 3 5 4 9 6 7 9 5 1 6 4 3 8 2 6 8 7 2 5 1 9 3 4 1 2 9 8 4 3 6 5 7 3 5 4 6 9 7 1 2 8])


(test/deftest valid-check
  (let [[check text] (control/check valid-solution)]
    (test/is (true? check))))

(test/deftest invalid-check
  (let [[check text] (control/check invalid-solution)]
    (test/is (false? check) "Invalid solution should be invalid")
    (test/is (not (nil? (re-find #"row" text))) text)))
