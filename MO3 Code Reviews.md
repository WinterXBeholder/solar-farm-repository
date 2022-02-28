
# Module 3 Code Reviews

## Requirements Checklist

* [ ] Features (each feature complete, works without errors)
    * [ ] Display Panels in a Section
    * [ ] Add a Panel
    * [ ] Update a Panel
    * [ ] Delete a Panel
* [ ] Rules (domain rules are complete)
* [ ] Testing (service and data components are tested with positive and negative cases)
* [ ] Layers (application logically groups models, data components, domain components, and UI components)
* [ ] Delimited File (data storage works even when the delimiter is part of a value)
* [ ] Classes (application uses multiple classes, each class manages a single responsibility)
* [ ] Methods (methods demonstrates single responsibility principle, belong in the class, and are right-sized)
* [ ] Naming (uses consistent and meaningful names for classes, variables, and methods)
* [ ] Java Idioms (excellent formatting and code style)
* [ ] Data Types (excellent choices for data types including enums, no unused variables)

## Test Plan

* [ ] On startup, displays a menu containing the following items:
    * [ ] Display All Panels in a Section
    * [ ] Create a Solar Panel
    * [ ] Update a Solar Panel
    * [ ] Delete a Solar Panel
* [ ] **Create a Solar Panel** with the following information:
    * Section: Test
    * Row: 1
    * Column: 1
    * Material: PolySi
    * Year Installed: 2000
    * Solar Tracking: No
    * [ ] A success message is displayed after the panel is created
* [ ] **Display All Panels in a Section** for section "Test"
    * [ ] Newly created panel is displayed
    * [ ] Information is displayed in an easy to read format
* [ ] Attempt to **Create a Solar Panel** using the same information as above
    * [ ] An error is displayed indicating that a duplicate panel cannot be created
* [ ] Attempt to **Create a Solar Panel** using the following information:
    * Section: Test2
    * Row: 251
    * Column: 251
    * Material: CdTe
    * Year: Current Year + 1
    * Solar Tracking: No
    * [ ] An error message is displayed for each invalid field
* [ ] **Update a Panel**
    * _If the application displays a list of existing panels to update then skip the next step in the test plan_
    * [ ] Enter Section: Test, Row: 50, Column: 50
        * [ ] An error message is displayed that no panel exists at that location
        * _If the application re-prompts for the panel to update, enter Section: Test, Row: 1, Column: 1 otherwise if the application doesn't re-prompt for the panel to update, then select **Update a Panel** again and enter in Section: Test, Row: 1, Column: 1_
    * [ ] Change the Material to "CdTe"
    * [ ] Change the Year Installed to "2001"
    * [ ] Change Solar Tracking to "Yes"
    * [ ] A success message is displayed after the panel is updated
* [ ] Select **Display All Panels in Section** for section Test
    * [ ] Panel in Section: Test, Row: 1, Column: 1 is displayed with the updated values
* [ ] **Update a Panel**
    * [ ] Enter Section: Test, Row: 1, Column: 1
    * [ ] Change the Material
        * [ ] Attempt to enter an invalid material value
        * [ ] An error message is displayed that the value is not a valid material
        * [ ] Enter a valid material value when re-prompted
    * [ ] Change the Year Installed
        * [ ] Attempt to change the year installed to current year + 1
        * [ ] An error message is displayed that the value is not a valid year
        * [ ] Enter a valid year installed when re-prompted
    * [ ] Change the Solar Tracking
        * [ ] Attempt to enter an invalid solar tracking value
        * [ ] An error message is displayed that the value is not a valid solar tracking value
        * [ ] Enter a valid solar tracking value when re-prompted
    * [ ] A success message is displayed after the panel is updated
* [ ] Select **Display All Panels in Section** for section Test
    * [ ] Panel in Section: Test, Row: 1, Column: 1 is displayed with the updated values
* [ ] **Delete a Panel**
    * _If the application displays a list of existing panels to delete then skip the next step in the test plan_
    * [ ] Enter Section: Test, Row: 50, Column: 50
        * [ ] An error message is displayed that no panel exists at that location
        * _If the application re-prompts for the panel to update, enter Section: Test, Row: 1, Column: 1 otherwise if the application doesn't re-prompt for the panel to delete, then select **Delete a Panel** again and enter in Section: Test, Row: 1, Column: 1_
    * _If prompted, confirm the deletion_
    * [ ] A success message is displayed after the panel is deleted
* [ ] Select **Display All Panels in Section** for section Test
    * [ ] Panel in Section: Test, Row: 1, Column: 1 is not displayed