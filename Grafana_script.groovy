
import hudson.model.*

//get current build
def build = Thread.currentThread().executable

// grafana url for aggregate dashboard - replace time stamp with %s
def perfResult = "http://100.20.0.129:3000/d/D5xRSBdVk/robot-reports?orgId=1&from=%s&to=%s"
def jmxMonitor = "http://100.20.0.129:3000/dashboard/db/jmx-monitor?from=%s&to=%s"

// get build start and end time
def start = build.getStartTimeInMillis();
def end = start + build.getExecutor().getElapsedTime();

// replace time
perfResult = String.format(perfResult, start, end);
jmxMonitor = String.format(jmxMonitor, start, end);

//build the string to be added as description.
def link = "<a href='%s'>%s</a><br/>";
def sb = new StringBuilder();
sb.append(String.format(link, perfResult, "Grafana Performance Result"))  .append(String.format(link, jmxMonitor, "Grafana JMX Result"));

// set build description
build.setDescription(sb.toString());

