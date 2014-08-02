(ns sudoku.control
  (:require [sudoku.tools :as tools]))

(defn get-cols [board]
  (map #(tools/get-col board %) (range 0 9)))

(defn get-rows [board]
  (map #(tools/get-row board %) (range 0 9)))

(defn get-groups [board]
  (map #(tools/get-group board %1 %2) [0 0 0 3 3 3 6 6 6]
                                      [0 3 6 0 3 6 0 3 6]))

(defn not-valid? [group]
  (-> (sort group)
      (= (seq [1 2 3 4 5 6 7 8 9]))
      (not)))

(defn check [board]
  (cond
    (some #(= 0 %) board) [false "Board not finished!"]
    (some not-valid? (get-rows board)) [false "Invalid row!"]
    (some not-valid? (get-cols board)) [false "Invalid col!"]
    (some not-valid? (get-groups board)) [false "Invalid group"]
    :else [true "Board is valid!"]))
