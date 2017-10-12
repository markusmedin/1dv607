title Create Member

"Main"->+"controller:YachtClub": startApplication()
"controller:YachtClub"->"controller:YachtClub": readerStoredData()
"controller:YachtClub"->"dataStorage:DataStorage": getMembers()
"controller:YachtClub"->"a_ui:UI": displayWelcomeMessage()
"controller:YachtClub"->"a_ui:UI": selectedMenuItem = readUserInt()
"controller:YachtClub"->+"controller:YachtClub": createMember()
"controller:YachtClub"->"a_ui:UI": createMember()
"controller:YachtClub"->"a_ui:UI": selectName()
"controller:YachtClub"->"a_ui:UI": memberName = readUserString()
"controller:YachtClub"->"a_ui:UI": selectPersonalNo()
"controller:YachtClub"->"a_ui:UI": memberPersonalNo = readUserInt()
"controller:YachtClub"->"controller:YachtClub": memberExists()
deactivate "controller:YachtClub"
"controller:YachtClub"->+"controller:YachtClub": saveData()
"controller:YachtClub"->"dataStorage:DataStorage": storeMembers()
