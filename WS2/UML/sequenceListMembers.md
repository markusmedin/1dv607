title List Members

"Main"->+"controller:YachtClub": startApplication()
"controller:YachtClub"->"controller:YachtClub": readerStoredData()
"controller:YachtClub"->"dataStorage:DataStorage": getMembers()
"controller:YachtClub"->"a_ui:UI": displayWelcomeMessage()
"controller:YachtClub"->"a_ui:UI": selectedMenuItem = readUserInt()
"controller:YachtClub"->+"controller:YachtClub": listMembers()
alt no members stored in dataStorage
"controller:YachtClub"->"a_ui:UI": noMembers()
else members stored in dataStorage
"controller:YachtClub"->"a_ui:UI": listMembers()
"controller:YachtClub"->"a_ui:UI": detailedList = readUserInt()
alt detailed user list
"controller:YachtClub"->+"controller:YachtClub": showDetailedMemberList()
loop no of members
"controller:YachtClub"->"a_ui:UI": printMessage()
"controller:YachtClub"->+"controller:YachtClub": listBoats()
"controller:YachtClub"->"a_ui:UI": printMessage()
loop no of boats
"controller:YachtClub"->"a_ui:UI": printMessage()
deactivate "controller:YachtClub"
end
end
else compact user list
"controller:YachtClub"->+"controller:YachtClub": showCompactMemberList()
loop no of members
"controller:YachtClub"->"a_ui:UI": printMessage()
deactivate "controller:YachtClub"
end
end
deactivate "controller:YachtClub"



