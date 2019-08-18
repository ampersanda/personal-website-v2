(ns ^:figwheel-hooks ampersanda-personal.widget.wave
  (:require [quil.core :as   q
             :include-macros true]
            [quil.middleware :as m]
            [ampersanda-personal.states :as state]
            [ampersanda-personal.subs.cursor-state :refer [expanded?]]))


(def brick-width 40)
(def brick-height 15)
(def cols 20)
(def rows 24)
(def column-offset 60)
(def row-offset 30)
(def rotation-increment 0.15)

(defn setup []
  (q/frame-rate 1)
  (q/color-mode :rgb) {})

(defn- draw [state]
  (q/background 255 127 80)
  (q/smooth)
  (q/no-fill)

  (q/stroke-weight 2)
  (q/stroke 255)

  (q/translate
   [30 30]

   (doseq [i (range cols)]
     (q/with-translation
      [(* i column-offset) 0]
      (let [r   (atom (q/random (- q/QUARTER-PI) q/QUARTER-PI))
            dir (atom 1)]
        (doseq [j (range rows)]
          (q/with-translation
           [0 (* row-offset j)]

           (q/with-rotation
            [@r]
            (q/rect (/ (- brick-width) 2) (/ (- brick-height) 2) brick-width brick-height)))

          (swap! r #(+ % (* @dir rotation-increment)))
          (when (or (> @r q/QUARTER-PI) (< @r (- q/QUARTER-PI)))
            (swap! dir #(* % -1)))))))))

(defn execute! [host width height]
  {:pre [(and (string? host)
              (number? width)
              (number? height))]}
  (q/defsketch wave
    :host host
    :size [width height]
    :setup setup
    :draw draw
    :middleware [m/fun-mode]))
