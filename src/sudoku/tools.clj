(ns sudoku.tools)

(defn group [n]
  (cond (> 3 n) #{0 1 2}
        (> 6 n) #{3 4 5}
        :else   #{6 7 8}))

(defn get-row [board row]
  (let [first-index (* row 9)]
    (->> (drop first-index board)
        (take 9))))

(defn get-col [board col]
  (keep-indexed (fn [index item]
                  (if (= (mod index 9) col)
                      item
                      nil)) board))

(defn get-group [board row col]
  (let [rows (group row)
        cols (group col)
        same-rows (flatten (map #(get-row board %) rows))]
    (flatten (map #(get-col same-rows %) cols))))


