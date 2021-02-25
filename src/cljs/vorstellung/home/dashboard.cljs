(ns vorstellung.home.dashboard
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   [re-echarts.core :as echarts]))

(defn energy-generation []
  [:> echarts/ECharts
   {:style {:width "100%" :height "50vh"}
    :theme "light"
    :option
    {:title {:text "chart"}
     :dataset {:dimention [:Week :Value]
               :source [{:Week "Mon" :Value 820}
                        {:Week "Tue" :Value 932}
                        {:Week "Wed" :Value 901}
                        {:Week "Thu" :Value 934}
                        {:Week "Fri" :Value 1220}
                        {:Week "Sat" :Value 820}
                        {:Week "Sun" :Value 990}]}
     :xAxis {:type "category"}
     :yAxis {:type "value"}
     :series [{:type "line"
               :smooth true}]}}])

(defn page []
  [:> m/Container {:max-width "lg" :disableGutters true}
   [:> m/Grid {:container true :spacing 3}
    [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}
     [energy-generation]]]])
