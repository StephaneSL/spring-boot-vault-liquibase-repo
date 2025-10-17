import jenkins.model.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.UserRemoteConfig
import hudson.plugins.git.GitSCM
import hudson.scm.BranchSpec

def instance = Jenkins.getInstance()
def jobName = "springboot-vault-liquibase-pipeline"
if (instance.getItem(jobName) == null) {
    println("Creating job: " + jobName)
    def scm = new GitSCM(
        [new UserRemoteConfig("https://github.com/StephaneSL/spring-boot-vault-liquibase-repo.git", "", "", "")],
        [new BranchSpec("*/main")],
        false, [], null, null, []
    )
    def job = instance.createProject(WorkflowJob, jobName)
    job.setDefinition(new CpsScmFlowDefinition(scm, "Jenkinsfile"))
    job.save()
} else {
    println("Job already exists: " + jobName)
}
