﻿
fastui.locale = "en-US";

/* Date
-----------------------------------------------------------------------------*/

fastui.dateInfo = {
    monthsLong: ["January", "Febraury", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
    daysLong: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
    daysShort: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
    quarterLong: ['Q1', 'Q2', 'Q3', 'Q4'],
    quarterShort: ['Q1', 'Q2', 'Q3', 'Q4'],
    halfYearLong: ['first half', 'second half'],
    patterns: {
        "d": "M/d/yyyy",
        "D": "dddd, MMMM dd, yyyy",
        "f": "dddd, MMMM dd, yyyy H:mm tt",
        "F": "dddd, MMMM dd, yyyy H:mm:ss tt",
        "g": "M/d/yyyy H:mm tt",
        "G": "M/d/yyyy H:mm:ss tt",
        "m": "MMMM dd",
        "o": "yyyy-MM-ddTHH:mm:ss.fff",
        "s": "yyyy-MM-ddTHH:mm:ss",
        "t": "H:mm tt",
        "T": "H:mm:ss tt",
        "U": "dddd, MMMM dd, yyyy HH:mm:ss tt",
        "y": "MMM, yyyy"
    },
    tt: {
        "AM": "AM",
        "PM": "PM"
    },
    ten: {
        "Early": "Early",
        "Mid": "Mid",
        "Late": "Late"
    },
    today: 'Today',
    clockType: 24
};


/* Calendar
-----------------------------------------------------------------------------*/

if (fastui.Calendar) {
    fastui.copyTo(fastui.Calendar.prototype, {
        firstDayOfWeek: 0,
        todayText: "Today",
        clearText: "Clear",
        okText: "OK",
        cancelText: "Cancel",
        daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
        format: "MMM, yyyy",

        timeFormat: 'H:mm'
    });
}


/* Pager
-----------------------------------------------------------------------------*/

if (fastui.Pager) {
    fastui.copyTo(fastui.Pager.prototype, {
        firstText: "First",
        prevText: "Prev",
        nextText: "Next",
        lastText: "Last",
        pageSizeText: "Per page："
    });
}

/* Gantt
-----------------------------------------------------------------------------*/
if (window.fastui.Gantt) {
    fastui.GanttView.ShortWeeks = [
        "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"
    ];
    fastui.GanttView.LongWeeks = [
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    ];

    fastui.Gantt.PredecessorLinkType = [
        { ID: 0, Name: 'Finish-Finish(FF)', Short: 'FF' },
        { ID: 1, Name: 'Finish-Start(FS)', Short: 'FS' },
        { ID: 2, Name: 'Start-Finish(SF)', Short: 'SF' },
        { ID: 3, Name: 'Start-Start(SS)', Short: 'SS' }
    ];

    fastui.Gantt.ConstraintType = [
        { ID: 0, Name: 'The sooner the better' },
        { ID: 1, Name: 'The later the better' },
        { ID: 2, Name: 'Must be begin in' },
        { ID: 3, Name: 'Must be completed in' },
        { ID: 4, Name: 'Beginning no earlier than ...' },
        { ID: 5, Name: 'Beginning no later than ...' },
        { ID: 6, Name: 'Completed no earlier than ...' },
        { ID: 7, Name: 'Completed no later than ...' }
    ];

    fastui.copyTo(fastui.Gantt, {
        ID_Text: "ID",
        Name_Text: "Name",
        PercentComplete_Text: "Progress",
        Duration_Text: "Duration",
        Start_Text: "Start",
        Finish_Text: "Finish",
        Critical_Text: "Critical",

        PredecessorLink_Text: "PredecessorLink",
        Work_Text: "Work",
        Priority_Text: "Priority",
        Weight_Text: "Weight",
        OutlineNumber_Text: "OutlineNumber",
        OutlineLevel_Text: "OutlineLevel",
        ActualStart_Text: "ActualStart",
        ActualFinish_Text: "ActualFinish",
        WBS_Text: "WBS",
        ConstraintType_Text: "ConstraintType",
        ConstraintDate_Text: "ConstraintDate",
        Department_Text: "Department",
        Principal_Text: "Principal",
        Assignments_Text: "Assignments",

        Summary_Text: "Summary",
        Task_Text: "Task",
        Baseline_Text: "Baseline",
        LinkType_Text: "LinkType",
        LinkLag_Text: "LinkLag",
        From_Text: "From",
        To_Text: "To",

        Goto_Text: "Goto",
        UpGrade_Text: "UpGrade",
        DownGrade_Text: "DownGrade",
        Add_Text: "Add Task",
        Edit_Text: "Edit Task",
        Remove_Text: "Remove Task",
        Move_Text: "Move Task",
        ZoomIn_Text: "ZoomIn",
        ZoomOut_Text: "ZoomOut",
        Deselect_Text: "Un Select",
        Split_Text: "Split Task"


    });
}