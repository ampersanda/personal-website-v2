(ns ^:figwheel-hooks ampersanda-personal.widget.pointer-link
  (:require [ampersanda-personal.events :refer [>cursor-mode !!!cursor-mode]]))

(defn +pointer-event [attrs]
  (conj {:on-mouse-over  (fn [e]

                           ;; FIXME : add sticky effect
                           (let [el (.-target e)
                                 width (js/parseFloat
                                        (-> el
                                            (js/getComputedStyle)
                                            (.-width)))
                                 height (js/parseFloat
                                         (-> el
                                             (js/getComputedStyle)
                                             (.-height)))]

                             (>cursor-mode :expand)))
         :on-mouse-leave #(!!!cursor-mode)} attrs))
