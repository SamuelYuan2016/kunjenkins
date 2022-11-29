import java.time.LocalDateTime

pipeline {
    agent any
    options { timestamps()
                retry(3)
        
    }
    
    stages {
        stage('First') {
            environment{
                wifipassword=credentials('wifisecret')
            }  
            steps {
                script {
            //if (1==1) {
            if (LocalDateTime.now().hour > 20) {
            sh "sleep 1s"
            sh "ls -la"
            sh 'ssh kqd@yx.jzymcs.cn   "echo $wifipassword  | sudo -S shutdown -t 300 "'
            
            }
            else {
                echo 'I execute elsewhere'
            }                        
        }
            }
        }

    
        stage('SecondStage') {
            environment{
                wifipassword=credentials('wifisecret')
               
            }
            options{
                timeout(time: 1, unit:"MINUTES")
            }
            
            steps{
                
            
            sh 'ssh kqd@kuncts02.jzymcs.cn -p 7022  "echo $wifipassword | sudo -S shutdown -t 300 "'
            sh 'ssh kqd@kuncts02.jzymcs.cn -p 7026   "echo $simplepw | sudo -S shutdown -t 300 "'
            
            }
        }
        stage('s3') {
            steps {
            
            sh 'pwd'
            
            }
        }
        
        }
        post { 
        always { 
            echo 'I will always say Hello again!'
    }
    
    } 
}


