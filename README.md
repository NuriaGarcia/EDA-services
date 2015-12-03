# EDA-services
A service that provides a basic interface for enabling Exploratory Data Analysis (EDA) by APIs RESTful of SPARQL Endpoints.

The xLiMe Dataset Summarisation service aims to provide a basic interface for enabling Exploratory Data Analysis (EDA), which is an a pproach to analysing data sets to summarize their main features, often with visual methods. In the context of xLiMe, the challenge is to provide services to users in order to give them an overview of the xLiMe dataset and provide them with anchor points to further explore the data. 

The xLiMe Dataset Summarisation API provides count and histogram data for various xLiMe specific entities, such as media items (tv programmes, social media posts, news articles).

API Usage Examples

http://localhost:8080/EDAServices/xlime-data-summary/counters?action=getCounts&object=triples,microposts,newsarticles,mediaresources,activities&format=json

http://localhost:8080/EDAServices/xlime-data-summary/histograms?action=getHistogram&object=instancesPerClass&format=json 
