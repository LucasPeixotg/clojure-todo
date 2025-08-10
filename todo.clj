(defn add-task [title tasks] 
     (conj tasks {
      :id (count tasks)
      :title title
      :done false}))

(defn toggle-task-completion [id tasks] 
  (let [title (:title (get tasks id))
        done (:done (get tasks id))] 
				(assoc tasks id {:id id
                     :title title
                     :done (not done)})))


(defn main-loop [tasks] (println "Commands: add, list, check, quit")
  (let [cmd (read-line)]
    (cond
      (= cmd "add")
      (do (print "Title: ") (flush)
          (let [title (read-line)
                new-tasks (add-task title tasks)]
            (main-loop new-tasks)))
      (= cmd "list")
      (do
        (doseq [task tasks]
          (let [id (get task :id)
                title (get task :title)
                done (get task :done)]
            (println (str id " [" (if done "X" " ") "] " title))))
        (main-loop tasks))
						(= cmd "check") (do (print "Id: ") (flush)
                 						(let [id-str (read-line)
      																							id (Integer/parseInt id-str)]
  																							(if (and (< id (count tasks)) (>= id 0))
  																									(let [new-tasks (toggle-task-completion id tasks)] (main-loop new-tasks))
																											(do (println "Invalid ID.") (main-loop tasks)))
																									))
      (= cmd "quit") (println "Goodbye!")
						:else (do (println "Invalid command...") (main-loop tasks))
						)))

(def tasks [])
(main-loop tasks)