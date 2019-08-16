(ns ^:figwheel-hooks ampersanda-personal.pages.home
  (:require
    [ampersanda-personal.events :as ev]
    [ampersanda-personal.routes :as routes]
    [ampersanda-personal.states :as state]
    [ampersanda-personal.widget.pointer-link :refer [+pointer-event]]
    [reagent.core :as reagent]))

(defn template []
 [:main
  {:class "content"}
  [:h1 {:class "jumbo"} "Hello!"]
  [:h1 "My name is Mochamad Lucky Pradana. I am Front End Developer and (ocassionally) Flutter Developer who live in Surabaya, Indonesia.
  "]])
