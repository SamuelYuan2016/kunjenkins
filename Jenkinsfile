import java.time.LocalDateTime

pipeline {
   agent any 
   options { timestamps()
                retry(2)
        
    }
    stages{
        stage("shutbyparallel"){

                  parallel{
                        stage('shutubt26') {
                            environment{simplepw=credentials('simplesecret')}
                            options{timeout(time: 1, unit:"MINUTES")}
                            
                            steps { sh 'ssh ubt26   "echo $simplepw | sudo -S shutdown -t 300 "'}
                        }
                        stage('shutrhl02'){
                              environment{ wifipassword=credentials('wifisecret')}
                              options{timeout(time: 1, unit:"MINUTES")}
                            steps {sh 'ssh rhl02  "echo $wifipassword | sudo -S shutdown -t 300 "'}
                        }    
                        stage('shutubt28'){
                            environment{wifipassword=credentials('wifisecret')}  
                            options{timeout(time: 1, unit:"MINUTES")}
                                steps {
                                    script {
                                            if (LocalDateTime.now().hour > 20) {
                                                    sh 'ssh ubt28   "echo $wifipassword  | sudo -S shutdown -t 300 "'
            
                                                    }
                                            else {
                                                echo 'Production Server can only shutdown at night after 20:00'
                                                    }                        
                                }
                        }
                      }
        
                        }

                        }
                  }
        
        post {
        always {
            echo 'Always'
        }
        success {
            echo 'Only on SUCCESS'
        }
        failure {
            echo 'Only on Failure'
        }
        unstable {
            echo 'Only if run is unstable'
        }
        changed {
            echo 'Only if status changed from Success to Failure or vice versa w.r.t. last run.'
        }
    }
                  
    }                           

                            
                   
        
