# TrailSnap Coding Exercise

Welcome! This exercise is designed to assess your Android development skills, your ability to integrate instrumentation tooling, and your awareness of accessibility best practices.

## Your Mission

You'll be working with TrailSnap, a simple hiking trails app. Your task is to add telemetry tracking to the search feature, measure performance, and make an accessibility improvement.

**Important**: This exercise is timeboxed to approximately **90 minutes**. You are **not expected to complete everything**. We're more interested in:
- Your approach to problem-solving
- Code quality and style
- How you prioritize tasks
- Your communication (comments, commit messages)

## Before You Begin

1. Ensure you've completed the setup in [README.md](README.md)
2. Verify the app runs successfully on your emulator/device
3. Confirm you can see the `startup` event in your Honeycomb dataset
4. Familiarize yourself with the codebase structure

## Challenge 1: Track Search Behavior

### Goal
Add telemetry events to track when users search for trails. This will help us understand:
- What terms users are searching for
- How often the search feature is used
- Whether users are finding what they need

### Requirements

Track search events by sending telemetry whenever a user performs a search:

1. **Add a search event** that fires when the user taps the "Go" button
2. **Include relevant attributes** in the event:
   - `search_term`: The text the user entered
   - `results_count`: The number of trails returned by the search
   - Any other attributes you think would be valuable

### Implementation Hints

- The search button click handler is in [MainActivity.kt](app/src/main/java/com/honeycomb/trailsnap/MainActivity.kt)
- Use `TelemetryClient.trackEvent()` to send events
- The event name should be descriptive (e.g., `"search"`)
- Consider where in the code flow it makes most sense to capture this data

### Verification

After implementing:
1. Run the app and perform a few searches with different terms
2. Check your Honeycomb dataset for `search` events
3. Verify all expected attributes are present
4. Try edge cases (empty search, no results, etc.)

## Challenge 2: Add Performance Metrics

### Goal
Measure how long search operations take. This helps us:
- Identify performance bottlenecks
- Set performance budgets
- Monitor performance over time

### Requirements

Add a `duration` attribute to your search events that measures how long the search operation takes:

1. **Measure search duration** from when the search starts to when results are available
2. **Add the measurement** as a `duration` attribute in your search event
3. **Consider the measurement location** - where in the code should you start and stop the timer?

### Implementation Hints

- The search operation happens in [TrailListViewModel.kt](app/src/main/java/com/honeycomb/trailsnap/viewmodel/TrailListViewModel.kt)
- Consider whether you should measure in the ViewModel, Repository, or Activity layer
- Think about what you're measuring: just the repository call, or the full user-initiated search flow?
- Think about the granularity of the time measurement. What makes most sense?

### Verification

After implementing:
1. Perform several searches and check the `duration` values in Honeycomb
2. Verify the timing makes sense (should be very fast for in-memory search)
3. Consider: Are there other durations you might want to track?

## Challenge 3: Fix an Accessibility Issue

### Goal
Improve the app's accessibility to make it more usable for people with disabilities. This demonstrates awareness of inclusive design principles.

### Background

The Android accessibility guidelines (following WCAG 2.1) recommend:
- **Minimum touch target size**: 48dp × 48dp
- **Minimum text size**: 14sp for body text
- **Content descriptions**: For interactive elements used by screen readers

### Requirements

1. Identify an accessibility issue in the search UI
2. Fix the issue
3. Test your fix

### Verification

After implementing:

1. Consider: Are there other accessibility improvements you might make?

## Bonus Challenges (Optional)

If you finish early and want to explore further, consider:

### Telemetry Enhancements
- Add a filter for trail difficulty and track it in search events
- Track when users tap on individual trail items
- Add error tracking for failed operations

### Performance Improvements
- Track startup time from app launch to UI ready
- Measure RecyclerView bind times
- Add memory usage metrics

### Code Quality
- Add unit tests for the telemetry client
- Extract hardcoded strings to resources
- Add error handling for edge cases

## Submission Guidelines

### What to Submit

Please provide:
1. Your modified code (entire project or changed files)
2. A brief summary of:
   - What you implemented
   - Any decisions or trade-offs you made
   - What you would do differently with more time
   - Any issues you encountered

### Submission Format

- **Private GitHub repository**: Share access with your interviewer

### Time Management

Remember this is timeboxed to ~90 minutes:
- **0-15 min**: Review code, understand architecture
- **15-45 min**: Challenge 1 (search tracking)
- **45-65 min**: Challenge 2 (performance metrics)
- **65-85 min**: Challenge 3 (accessibility fix)
- **85-90 min**: Final testing and summary writeup

Don't worry if you don't finish—we'd rather see quality work on fewer challenges than rushed work on all three.

## Evaluation Criteria

We'll be looking at:

### Code Quality
- Clean, readable, idiomatic Kotlin
- Appropriate use of Android patterns and APIs
- Proper error handling
- Good naming and code organization

### Technical Implementation
- Telemetry events are correctly structured
- Performance measurement is accurate
- Accessibility fix follows best practices
- Code is properly integrated with existing architecture

### Problem-Solving
- Understanding of requirements
- Good judgment on implementation details
- Consideration of edge cases
- Thoughtful decision-making

### Communication
- Clear code comments where helpful
- Meaningful commit messages
- Good summary of your approach
- Honest assessment of trade-offs

## Questions?

If you have questions or encounter blocking issues:
- Review the [README.md](README.md) for setup help
- Check Android Studio Logcat for error messages
- Contact your interviewer if you're stuck on setup (not the coding challenges)

## Resources

You're welcome to use any resources you normally would:
- Android documentation
- Kotlin documentation
- Honeycomb API documentation
- Stack Overflow, blog posts, etc.
- AI assistants or code completion tools

We want to see how you work in a realistic environment.

---

Good luck, and have fun! We're excited to see your approach to these challenges.
