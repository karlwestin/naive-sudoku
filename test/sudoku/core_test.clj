(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]
            [sudoku.control :as control]
            [sudoku.examples :as examples]))


(deftest solve-easy
  (let [[status board] (solve examples/easy2)
        [check text] (control/check board)]
    (is (true? status) "Should solve the sudoku")
        (true? check) "Solution should be valid"))

(deftest not-solve-hard
  (let [[status board] (solve examples/hard)]
    (is (false? status) "Shouldn't solve this one")))
