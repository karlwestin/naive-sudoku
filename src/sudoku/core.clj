(ns sudoku.core
  (:gen-class)
  (:require [sudoku.tools :as tools]))

(defn check-item [item index board number]
  (let [col (mod index 9)
        row (int (/ index 9))]
    (cond
      (= item number) number
      (< 0 item) "N"
      ;; Any in the same group?
      (some #(= number %) (tools/get-group board row col)) "N"
      ;; Any in the same row?
      (some #(= number %) (tools/get-row   board row)) "N"
      ;; Any in the same col?
      (some #(= number %) (tools/get-col   board col)) "N"
      :else "*")))

(defn is-only-poss [item index board]
  (when (= "*" item)
    (let [col (mod index 9)
          row (int (/ index 9))
          group (->> (tools/get-group board row col)
                     (filter #(= "*" %)))
          row (->> (tools/get-row board row)
                   (filter #(= "*" %)))
          col (->> (tools/get-col board col)
                   (filter #(= "*" %)))]
      (or  (= 1 (count group))
           (= 1 (count row))
           (= 1 (count col))))))

(defn update-board [number board possible]
  (map-indexed (fn [index item]
    (let [poss (nth possible index)
          solved (true? poss)]
      (if solved
        number
        item))) board))

(defn fill-in-board [number board]
  (let [analyzed (map-indexed #(check-item %2 %1 board number) board)
        possible (map-indexed #(is-only-poss %2 %1 analyzed) analyzed)
        found (some true? possible)]

      (if found
          (update-board number board possible)
          board)))

(defn finished? [board]
  (not-any? #(= 0 %) board))

(defn my-pp [board]
  (let [rows (partition 9 board)]
  (doseq [a rows] (println a))))

(defn solve [board]
  (let [tries (range 1 10)
        new-board (reduce (fn [board number]
                            (let [new-board (fill-in-board number board)]
                                  new-board)) board tries)
        ;; If the new and old board are the same
        ;; we haven't been able to fill anything in
        not-found (= new-board board)]
      (cond
        not-found (do (println "Could not solve!") [false new-board])
        (finished? new-board) (do (println "Yay! We won!") [true new-board])
        :else (solve new-board))))

;; is-taken
;; is-num
;; has-within-group
;; has-within-row
;; has-within-col
;; om alla är nej: *

;;[N N 2 N N N N N N
;; N N N N * N * * *
;; N N N * * * * N *
;;
;; N N N * * * N N *
;; 2 N N N * N * N N
;; N N N * * * N N N
;;
;; N 2 N N N N N N N
;; N N N N * N * * *
;; N N N * N * N * N]

;; On each star:
;; has-within-group *
;; has within-row *
;; has within-col *
;; om alla är nej: number
;; return board + found
