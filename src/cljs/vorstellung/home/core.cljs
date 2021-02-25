(ns vorstellung.home.core
  (:require
   [day8.re-frame.http-fx]
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [goog.events :as events]
   [goog.history.EventType :as HistoryEventType]
   [markdown.core :refer [md->html]]
   [reitit.core :as reitit]
   [reitit.frontend.easy :as rfe]
   [clojure.string :as string]
   [vorstellung.common.route :as route]
   [vorstellung.events]
   [vorstellung.common.ajax :as ajax]
   [vorstellung.header :as header]
   [vorstellung.home.page :as page]
   [vorstellung.home.dashboard :as dashboard])
  (:import goog.History))

(defn about-page []
  [:div
   [:img {:src "/img/warning_clojure.png"}]])

(def router
  (reitit/router
   [["/"        {:name        :home
                 :view        {:page #'dashboard/page
                               :tool (fn [] [:div])}
                 :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]
    ["/about"   {:name        :about
                 :view        {:page #'about-page
                               :tool (fn [] [:div])}}]]))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'header/page] (.getElementById js/document "app")))

(defn init! []
  (route/start-router! router route/navigate!)
  (ajax/load-interceptors!)
  (mount-components))
