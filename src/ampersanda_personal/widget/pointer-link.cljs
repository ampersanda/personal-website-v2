(ns ^:figwheel-hooks ampersanda-personal.widget.pointer-link
  (:require [ampersanda-personal.events :refer [>cursor-mode !!!cursor-mode]]))

(defn +pointer-event [attrs]
  (conj {:on-mouse-over  #(>cursor-mode :expand)
         :on-mouse-leave #(!!!cursor-mode)} attrs))
