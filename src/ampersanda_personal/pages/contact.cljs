(ns ^:figwheel-hooks ampersanda-personal.pages.contact
  (:require
    [ampersanda-personal.states :as state]
    [ampersanda-personal.routes :as routes]
    [reagent.core :as reagent]
    [ampersanda-personal.utils.shim :refer [render-with-shim]]))

(defn template []
  (render-with-shim
   [:main
    {:class "content editor"}
    [:h1 {:class "jumbo use-global-slant"} "Contact"]]))
